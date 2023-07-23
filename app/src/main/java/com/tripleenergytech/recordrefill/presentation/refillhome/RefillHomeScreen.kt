package com.tripleenergytech.recordrefill.presentation.refillhome

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.MyAppBar
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.presentation.refillhome.extra.AddItemsComponent
import com.tripleenergytech.recordrefill.presentation.refillhome.extra.RefillRecordItem
import com.tripleenergytech.recordrefill.presentation.refillhome.states.DaySessionState
import com.tripleenergytech.recordrefill.ui.theme.primaryColor
import com.tripleenergytech.recordrefill.ui.theme.secondaryColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefillHomeScreen(
    navController: NavController,
    viewModel: RefillHomeViewModel = hiltViewModel()
) {
    var daySession:DaySession?
    var isDaySessionActive = remember { mutableStateOf(false) }
    val refillRecords by viewModel.refillRecords.collectAsState()


    val state by viewModel.daySessionState.collectAsState()

    val activeCylinder by viewModel.activeCylinder.collectAsState()

    when(state){
        is DaySessionState.Empty -> Log.e("STATE OF REC","EMPTY")
        is DaySessionState.Present -> {
            (state as DaySessionState.Present).data.let { ds->
                Log.e("STATE OF REC","PRESENT")
                daySession =ds
                isDaySessionActive.value =true
            }
        }
        is DaySessionState.Error -> Log.e("STATE OF REC","ERROR")
    }

    LaunchedEffect(Unit) {
        Log.e("start get init sess","start")
        daySession = viewModel.getInitialDateSession()
        viewModel.getRefillRecords()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        MyAppBar("Gas Record Refill")
        Material3SheetExample()
        var showSheet by remember { mutableStateOf(false) }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(containerColor = primaryColor,
                    onClick = {
                        showSheet = true
                        //viewModel.changeState()

                        GlobalScope.launch {

                        }

                    },
                    content = {
                        Text(text = "Add", fontSize = Utils.TextSize.textMedium)
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .heightIn(75.dp)
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier,
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val modifier = Modifier
                    modifier.fillMaxWidth(.72f)
                    items(refillRecords) {record->
                         viewModel.getActiveCylinder(activeCylinderId = record.cylinder_id)
                        val activeCylinderName = activeCylinder?.cylinder_name
                        activeCylinderName?.let { it1 -> RefillRecordItem(record, activeCylinderName = it1) }
                    }
                }
                if (showSheet) {
                    ModalBottomSheet( // androidx.compose.material3.ModalBottomSheet
                        onDismissRequest = { showSheet = false },
                    ) {
                        Column {
                            AddItemsComponent(
                                navController=navController
                                ,viewModel,
                                isDaySessionActive
                            )
                        }

                    }
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3SheetExample() {

}


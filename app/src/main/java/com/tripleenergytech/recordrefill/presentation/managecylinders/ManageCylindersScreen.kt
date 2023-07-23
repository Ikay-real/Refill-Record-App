package com.tripleenergytech.recordrefill.presentation.managecylinders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.R
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.presentation.managecylinders.extras.CylinderListItem
import com.tripleenergytech.recordrefill.ui.components.RRAlertDialog

import com.tripleenergytech.recordrefill.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCylindersScreen(
    navController: NavController,
    viewModel: ManageCylindersViewModel= hiltViewModel()
) {
    val activeCylinders by viewModel.activeCylinderRecords.collectAsState()
    val daySessionExists = remember { mutableStateOf(false) }
    val showDaySessionDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Manage Cylinders",
                        color= Color.White,
                        fontWeight = FontWeight(600)
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(primaryColor)
            )
        },
        content = {paddingValues->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {

                RRAlertDialog(
                    title = "HJKUKIOKLN",
                    message ="hjkl;kjkkj" ,
                    showDialog = showDaySessionDialog.value,
                    onDismiss = {

                    },
                    onConfirm = {

                    }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val name = listOf(" qwrqwr", "qwrwr", "qwrrwrwq")
                    val modifier = Modifier
                    modifier.fillMaxWidth(.72f)
                    items(activeCylinders){activeCylinder->
                        CylinderListItem(activeCylinder)
                    }
                }
            }
        },
        floatingActionButton = {
           FloatingActionButton(
               containerColor = primaryColor,
               onClick = {
                   if(daySessionExists.value) navController.navigate(Utils.NavigationRoutes.ADD_CYLINDERS)
                   else showDaySessionDialog.value = true
                   //
               }) {
               Icon(
                   painter = painterResource(id = R.drawable.add_24),
                   contentDescription = "Add cylinder"
               )
           }
        }
    )
}
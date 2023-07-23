package com.tripleenergytech.recordrefill.presentation.refillrecord

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.MyAppBar
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.ui.components.TextBold
import com.tripleenergytech.recordrefill.ui.theme.primaryColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.lang.NumberFormatException

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun RefillRecordScreen(
    navController: NavController,
    viewModel: RefillRecordScreenViewModel = hiltViewModel(),
) {
    var cylinderId = remember {
        mutableStateOf("")
    }
    val currentState by viewModel.state.collectAsState()
    when(currentState){
        is RefillRecordState.EmptyState->{
            Log.e("state in refill","empty")
        }
        is RefillRecordState.Regular->{
            Log.e("state in refill","regular")
        }
        is RefillRecordState.CarryOver<*>->{
            Log.e("state in refill","carry over")
        }
        is RefillRecordState.Error->{
            Log.e("state in refill","error")
        }
        else -> {
            Log.e("state in refill","else")
        }
    }

    val activeCylinderRecords by viewModel.activeCylinderRecords.collectAsState()

    //Values
    val totalGasValue by viewModel.totalGasValue.collectAsState()
    val currentCylinder= remember{ mutableFloatStateOf(0.0f) }

    LaunchedEffect(key1 = 1) {
        Log.e(
            "Random string ${currentCylinder.value}",
            Utils.Tools.RandomIDGenerator.generateID(Utils.Tools.RandomIDGenerator.Companion.Type.CYLINDER_RECORD)
        )
    }

    val tareGasAmount = remember { mutableStateOf("") }
    val gasAmount = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(end = 15.dp)) {
                        Text(
                            text = "Record refill",
                            color= Color.White,
                            fontWeight = FontWeight(600)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Text(
                                    text = "Current cylinder: ",
                                    fontSize = 15.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "${currentCylinder.value} KG ",
                                    fontSize = 15.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight(600)
                                )
                            }

                            Row {
                                Text(
                                    text = "Total Gas: ",
                                    fontSize = 15.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = try {
                                        "${(totalGasValue - gasAmount.value.toFloat())} KG"
                                    }catch (nfe:NumberFormatException){
                                        "$totalGasValue"
                                    },
                                    fontSize = 15.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight(600)
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(primaryColor)
            )
        },

        content={
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())) {

            val modifier = Modifier
            modifier.fillMaxWidth(.72f)
            item {
                Column {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(23.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(.72f)
                                .padding(top = 55.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = "Record ID: ENT_374VJHW89EQE73",
                            textAlign = TextAlign.End,
                            fontSize = 14.sp
                        )
                        val refillRecordName = remember { mutableStateOf("") }

                        DropDownComponent(viewModel,activeCylinderRecords) {activeCylinder->
                            cylinderId.value = activeCylinder.active_cylinder_id
                            Log.e("DROPDOWN VALUE", activeCylinder.active_cylinder_id)
                            currentCylinder.value= activeCylinder.gas_weight
                        }
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(.72f),
                            value = refillRecordName.value,
                            onValueChange = { refillRecordName.value = it },
                            label = { Text(text = "Name") },
                        )

                        val tareAmount = remember { mutableStateOf("") }
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(.72f),
                            value = tareAmount.value,
                            onValueChange = { tareAmount.value = it },
                            label = { Text(text = "Tare amount") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(.72f),
                            value = gasAmount.value,
                            onValueChange = {
                                gasAmount.value = it
                                try {
                                    tareGasAmount.value ="${(gasAmount.value.toFloat()+tareAmount.value.toFloat())} KG"
                                }catch (e: Exception){
                                    Log.e("Error","${e.message}")
                                }
                            },
                            label = { Text(text = "Gas amount") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(.72f),
                            value = tareGasAmount.value,
                            onValueChange = { tareGasAmount.value = it},

                            enabled = false
                        )

                        Column(modifier = Modifier.padding(top = 30.dp, bottom = 40.dp)) {
                            val context = LocalContext.current
                            Button(
                                modifier = Modifier
                                    .height(53.dp)
                                    .width(180.dp),
                                onClick = {
                                    viewModel.addRefillRecord(
                                        cylinderId.value,
                                        refillRecordName.value,
                                        tareAmount.value.toFloat(),
                                        gasAmount.value.toFloat(),
                                    )
                                    Toast.makeText(context,"Record added",Toast.LENGTH_SHORT).show()
                                    navController.navigateUp()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                            ) {
                                TextBold(text = "Add")
                            }
                        }
                    }
                }
            }
        }
    },
        snackbarHost = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownComponent(
    viewModel: RefillRecordScreenViewModel,
    activeCylinderRecords: List<ActiveCylinder>,
    onTextChange: (ActiveCylinder) -> Unit
) {
    activeCylinderRecords
    val cylinders = arrayOf("Cylinder 1", "Cylinder 2", "Cylinder 3", "Cylinder 4", "Cylinder 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(cylinders[0]) }

    Box {
        LaunchedEffect(Unit) {
            Log.e("Compose", "disposable")
            //onTextChange(selectedText)
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth(.72f)
                    .menuAnchor(),
                label = { Text(text = "Select a cylinder") }
            )
            Log.e("cylinder available","${activeCylinderRecords.size}")
            ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(.72f),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
               activeCylinderRecords.forEachIndexed {index, option ->
                    DropdownMenuItem(
                        text = { Text(text = "${option.cylinder_name} (${option.gas_weight}) KG" )},
                        onClick = {
                            selectedText = option.cylinder_name
                            onTextChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
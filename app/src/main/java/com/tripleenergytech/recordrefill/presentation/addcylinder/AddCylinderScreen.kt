package com.tripleenergytech.recordrefill.presentation.addcylinder

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.MyAppBar
import com.tripleenergytech.recordrefill.R
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.presentation.refillrecord.RefillRecordScreenViewModel
import com.tripleenergytech.recordrefill.ui.components.TextBold
import com.tripleenergytech.recordrefill.ui.theme.primaryColor
import java.lang.NumberFormatException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCylinderScreen(
    navController: NavController,
    viewModel: AddCylinderScreenViewModel = hiltViewModel()
) {
    LazyColumn(Modifier.fillMaxSize()) {
        val modifier = Modifier
        modifier.fillMaxWidth(.72f)
        item {
            Column {
                MyAppBar(title = "Add cylinder")
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(23.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier.padding(top = 45.dp),
                        painter = painterResource(id = R.drawable.cylinder_icon),
                        contentDescription = Utils.descriptions["des_cylinder_image"]
                    )

                    val nameOfCylinder = remember { mutableStateOf("") }
                    OutlinedTextField(modifier = Modifier.fillMaxWidth(.72f),
                        value = nameOfCylinder.value,
                        onValueChange = { nameOfCylinder.value = it },
                        label = { Text(text = "Cylinder Name") }
                    )

                    val tareAmount = remember { mutableStateOf("") }
                    val gasAmount = remember { mutableStateOf("") }
                    val tareAndGasAmount = remember { mutableStateOf("") }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(.72f),
                        value = tareAmount.value,
                        onValueChange = {
                            tareAmount.value = it
                            try{
                                gasAmount.value = "${(tareAndGasAmount.value.toFloat() - tareAmount.value.toFloat())} KG"
                            } catch (nfe:NumberFormatException){
                                gasAmount.value = "${(0.0f - 0.0f)} KG"
                            }
                        },
                        label = { Text(text = "Tare weight") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )


                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(.72f),
                        value = tareAndGasAmount.value,
                        onValueChange = {
                            tareAndGasAmount.value =it
                            try{
                                gasAmount.value = "${(tareAndGasAmount.value.toFloat() - tareAmount.value.toFloat())} KG"
                            } catch (nfe:NumberFormatException){
                                gasAmount.value = "0 KG"
                            }

                        },

                        label = { Text(text = "Tare and mass and amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )


                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(.72f),
                        value = gasAmount.value,
                        onValueChange = { gasAmount.value = it },
                        label = { Text(text = "Gas weight") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        enabled = false
                    )
                    Column(modifier = Modifier.padding(top = 30.dp, bottom = 40.dp)) {
                        val context = LocalContext.current
                        Button(
                            modifier = Modifier
                                .height(53.dp)
                                .width(180.dp),
                            onClick = {
                                viewModel.addCylinder(
                                    nameOfCylinder.value,
                                    tareAmount.value.toFloat(),
                                    tareAndGasAmount.value.toFloat(),
                                    (tareAndGasAmount.value.toFloat()-tareAmount.value.toFloat())
                                    //gasAmount.value.toFloat()
                                )
                                Toast.makeText(context,"Record added", Toast.LENGTH_SHORT).show()
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
}
package com.tripleenergytech.recordrefill.presentation.refillhome.extra

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.tripleenergytech.recordrefill.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.presentation.refillhome.RefillHomeViewModel
import com.tripleenergytech.recordrefill.ui.theme.appTypography
import com.tripleenergytech.recordrefill.ui.theme.primaryColor


@Composable
fun AddItemsComponent(
    navController: NavController,
    viewModel: RefillHomeViewModel,
    isDaySessionActive: MutableState<Boolean>
) {
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    var showCantAddMoreDaySessionDialog by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 30.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            if (showDialog) {
                AlertDialog(
                    title = {
                        Text(text = "Refill Record",
                            fontWeight = FontWeight(600),
                            style = appTypography.titleLarge
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    onDismissRequest = { showDialog = false },
                    text = {
                        Text("Add a day session first", style = appTypography.bodyMedium)
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            Log.e("Dismiss button", "clicked $showDialog")
                            showDialog = false
                        }) {
                            Text("Close")
                        }
                    },
                )
            }



            if (showCantAddMoreDaySessionDialog) {
                AlertDialog(
                    title = {
                        Text(text = "Day session", textAlign = TextAlign.Start,
                            fontWeight = FontWeight(600),
                            style = appTypography.titleLarge
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    onDismissRequest = { showCantAddMoreDaySessionDialog = false },
                    text = {
                        Text(modifier = Modifier.fillMaxWidth(),
                            text = "Day Session already added",
                            textAlign = TextAlign.Center,
                            style = appTypography.bodyMedium
                        )
                    }, icon = {

                        val iconPainter = painterResource(R.drawable.error_24px)

                        // Display the icon using the Image composable
                        Image(
                            modifier = Modifier.width(36.dp),
                            painter = iconPainter,
                            contentDescription = "Icon"
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            Log.e("Dismiss button", "clicked $showCantAddMoreDaySessionDialog")
                            showCantAddMoreDaySessionDialog = false
                        }) {
                            Text("Close")
                        }
                    },
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SquareButton(

                    onClick = {
                        if (!isDaySessionActive.value) showDialog = true
                        else navController.navigate("refill_record_screen")
                    },
                    buttonText = "qk,jf"
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(
                            id = R.drawable.add_refill_record
                        ), contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Add record",
                    fontSize = Utils.TextSize.textSmall
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SquareButton(
                    onClick = {
                        if (!isDaySessionActive.value) showDialog2 = true
                        else showCantAddMoreDaySessionDialog = true
                    },
                    buttonText = "qk,jf"
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(
                            id = R.drawable.day_session
                        ), contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Add a day session",
                    fontSize = Utils.TextSize.textSmall
                )
            }
        }
    }
    TextFieldDialog(
        showDialog = showDialog2,
        onDismiss = { showDialog2 = false },
        onConfirm = { text ->
            textFieldValue = text
            showDialog2 = false
            viewModel.createDaySession()
        }
    )
}

@Composable
fun SquareButton(
    onClick: () -> Unit,
    buttonText: String,
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        modifier = Modifier.size(70.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        content = content
    )
}




@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (showDialog) {
        var text by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text("Day session",fontWeight = FontWeight(600))
            },
            text = {
                Column(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                ) {
                    val date = Utils.Tools.DateFormatter.getCurrentDateFormattedInBritishStandard()
                    Text(modifier = Modifier,
                        text = "Open day session for today on",
                        style = appTypography.titleMedium,
                        textAlign = TextAlign.Center

                    )
                    Text(modifier = Modifier.padding(top=5.dp),
                        text = date,
                        color = primaryColor,
                        style = appTypography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                /*
                    OutlinedTextField(
                        value = text,
                        onValueChange = {text = it },
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth(),
                        label = { Text(text = "Tare and mass and amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        })
                    )*/
                }


                /*BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    })
                )*/
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm(text)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        primaryColor
                    )
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        primaryColor
                    )
                ) {
                    Text("Dismiss")
                }
            }
        )

        LaunchedEffect(showDialog) {
            /*if (showDialog) {
                focusRequester.requestFocus()
            }*/
        }
    }
}
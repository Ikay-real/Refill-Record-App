package com.tripleenergytech.recordrefill.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.ui.theme.appTypography
import com.tripleenergytech.recordrefill.ui.theme.primaryColor

@Composable
fun RRAlertDialog(
    title: String,
    message:String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (showDialog) {
        var text by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(title, fontWeight = FontWeight(600))
            },
            text = {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    val date = Utils.Tools.DateFormatter.getCurrentDateFormattedInBritishStandard()
                    Text(
                        modifier = Modifier,
                        text = message,
                        style = appTypography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = date,
                        color = primaryColor,
                        style = appTypography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
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
    }
}
package com.tripleenergytech.recordrefill.ui.components

import android.service.autofill.OnClickAction
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

@Composable
fun RRButton(onClick:() ->Unit){
    Button(onClick = onClick) {

    }
}
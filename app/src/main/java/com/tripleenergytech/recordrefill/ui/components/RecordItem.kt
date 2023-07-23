package com.tripleenergytech.recordrefill.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RefillRecordItem(){
    Box(modifier = Modifier
        .height(200.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "hie")
            Text(text = "hie")
        }
    }

}
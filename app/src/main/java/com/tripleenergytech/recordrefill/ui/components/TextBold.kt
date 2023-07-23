package com.tripleenergytech.recordrefill.ui.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.tripleenergytech.recordrefill.common.Utils


@Composable
fun TextBold(text: String) {
    Text(
        modifier = Modifier, fontWeight = FontWeight(700),
        text = text,
        textAlign = TextAlign.End,
        //fontSize = textSizeSmall
    )
}

@Composable
fun TextBold(modifier: Modifier, text: String) {
    Text(
        modifier = modifier, fontWeight = FontWeight(700),
        text = text,
        textAlign = TextAlign.Start,
        fontSize = Utils.TextSize.textSmall
    )
}

@Composable
fun TextBold(modifier: Modifier, text: String,fontSize: TextUnit) {
    Text(
        modifier = modifier, fontWeight = FontWeight(700),
        text = text,
        textAlign = TextAlign.Start,
        fontSize = fontSize
    )
}
@Composable
fun TextBold(modifier: Modifier, text: String,fontSize: TextUnit,textAlign: TextAlign) {
    Text(
        modifier = modifier, fontWeight = FontWeight(700),
        text = text,
        textAlign = textAlign,
        fontSize = fontSize
    )
}
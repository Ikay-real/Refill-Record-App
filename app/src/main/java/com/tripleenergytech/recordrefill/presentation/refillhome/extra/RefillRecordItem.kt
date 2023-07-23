package com.tripleenergytech.recordrefill.presentation.refillhome.extra

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.ui.components.TextBold
import java.util.Date

@Composable
fun RefillRecordItem(refillRecord: RefillRecord,activeCylinderName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(bottom = 5.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                Arrangement.SpaceBetween,
            ) {
                TextBold(
                    modifier = Modifier,
                    text = activeCylinderName,
                    fontSize = Utils.TextSize.textSmallest
                )
                Text(
                    modifier = Modifier,
                    text = Utils.Tools.DateFormatter.convertLongToDate(refillRecord.timestamp),
                    fontSize = Utils.TextSize.textSmallest
                )
            }
            TextBold(
                modifier = Modifier,
                text = refillRecord.name,
                fontSize = Utils.TextSize.textMedium
            )
            Divider(
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = refillRecord.tare_weight.toString(),
                    fontSize = Utils.TextSize.textSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = refillRecord.tare_and_gas_weight.toString(),
                    fontSize = Utils.TextSize.textSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = refillRecord.gas_weight.toString(),
                    fontSize = Utils.TextSize.textSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = "__",
                    fontSize = Utils.TextSize.textSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = "__",
                    fontSize = Utils.TextSize.textSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}
package com.tripleenergytech.recordrefill.presentation.managecylinders.extras

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.ui.components.TextBold
import com.tripleenergytech.recordrefill.ui.theme.appTypography


@Composable
fun CylinderListItem(activeCylinder: ActiveCylinder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(12.dp)
        ) {
            Text(
                text = activeCylinder.active_cylinder_id,
                style = appTypography.labelSmall
            )
            /*TextBold(
                modifier = Modifier,
                text = activeCylinder.active_cylinder_id,

            )*/
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
                TextBold(
                    modifier = Modifier
                        .weight(.4f),
                    text = activeCylinder.cylinder_name
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = activeCylinder.tare_weight.toString(),
                    fontSize = Utils.TextSize.textSmall
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = activeCylinder.tare_and_gas_weight.toString(),
                    fontSize = Utils.TextSize.textSmall
                )
                Text(
                    modifier = Modifier
                        .weight(.2f),
                    text = activeCylinder.gas_weight.toString(),
                    fontSize = Utils.TextSize.textSmall
                )
            }
        }

    }
}
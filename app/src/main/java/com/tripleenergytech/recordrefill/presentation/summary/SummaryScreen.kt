package com.tripleenergytech.recordrefill.presentation.summary

import android.graphics.Paint.Align
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.squareup.moshi.internal.Util
import com.tripleenergytech.recordrefill.MyAppBar
import com.tripleenergytech.recordrefill.R
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.presentation.summary.extra.SalesSummaryBasicItem
import com.tripleenergytech.recordrefill.ui.theme.Purple
import com.tripleenergytech.recordrefill.ui.theme.appTypography
import com.tripleenergytech.recordrefill.ui.theme.primaryColor

@Composable
fun SummaryScreen(
    navController: NavController
  ,viewModel:SummaryViewModel = hiltViewModel()
) {
    val activeCylinders by viewModel.activeCylinderRecords.collectAsState()
    val cylinderCount by viewModel.gasCylinderCount.collectAsState()
    val scrollState = rememberScrollState()


    //
    val daySessions by viewModel.daySessions.collectAsState()
    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
        MyAppBar(title = "Summary")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .background(
                        color = Purple,
                        shape = RoundedCornerShape(bottomStart = 46.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(9.dp), verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .weight(.5f)
                            .padding(start = 20.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = "Gas levels (total)",
                            style = appTypography.bodyLarge,
                            color = Color.White
                        )
                        Text(
                            modifier = Modifier,
                            text = "45KG",
                            style = appTypography.headlineLarge,
                            color = Color.White,
                            fontSize = 60.sp,
                            fontWeight = FontWeight(600)
                        )
                        //Spacer(modifier = Modifier.padding(bottom = 16.dp))
                    }
                    HalfStyledImage(
                        modifier = Modifier.weight(.4f),
                        R.drawable.gas_cylinder
                    )
                    /*Image(
                        modifier=Modifier.weight(.4f),
                        painter = painterResource(id = R.drawable.gas_cylinder) ,
                        contentDescription = "Gas cylinder"
                    )*/
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(primaryColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp)
                        .clickable {
                            navController.navigate(Utils.NavigationRoutes.MANAGE_CYLINDERS)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 35.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "$cylinderCount active",
                                style = appTypography.headlineMedium,
                                color = Color.White,
                                fontWeight = FontWeight(600)
                            )
                            Text(
                                text = "Manage cylinders",
                                style = appTypography.bodyMedium,
                                color = Color.White
                            )
                        }
                        Image(
                            modifier = Modifier.width(70.dp),
                            painter = painterResource(id = R.drawable.gas_tank),
                            contentDescription = "Day",
                        )
                    }
                }


                Card(
                    colors = CardDefaults.cardColors(primaryColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp)
                        .clickable {
                            Log.e("Clickable", "iuwrtwui")
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 35.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${daySessions.size}",
                                style = appTypography.headlineMedium,
                                color = Color.White,
                                fontWeight = FontWeight(600)
                            )
                            Text(
                                text = "Day Sessions",
                                style = appTypography.bodyMedium,
                                color = Color.White
                            )
                        }

                        Row {
                            Image(
                                modifier = Modifier
                                    .width(33.dp),
                                painter = painterResource(id = R.drawable.day_session),
                                contentDescription = "Day",
                            )
                            Spacer(modifier = Modifier.padding(end = 20.dp))
                        }

                    }
                }

                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {

                        },
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = "Sales summary",
                        style = appTypography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    for(i in 1.. 3){
                        SalesSummaryBasicItem()
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "See more",
                        style = appTypography.bodyMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
fun HalfStyledImage(modifier: Modifier, imageRes: Int) {
    val shape: Shape = RectangleShape

    Box(modifier = modifier.width(100.dp)) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (60).dp) // Adjust the offset as needed
                .clip(shape)
        )
    }
}


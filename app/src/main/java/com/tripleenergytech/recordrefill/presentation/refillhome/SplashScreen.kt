package com.tripleenergytech.recordrefill.presentation.refillhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripleenergytech.recordrefill.R
import com.tripleenergytech.recordrefill.common.Utils
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier= Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.splash_screen_intro),
            contentDescription = "Splash screen Image",
        )

        
    }
    LaunchedEffect(Unit) {
        delay(500L)
        navController.navigate(Utils.NavigationRoutes.SPLASH_SCREEN)
    }
}
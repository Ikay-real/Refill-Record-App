package com.tripleenergytech.recordrefill

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.squareup.moshi.internal.Util
import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.presentation.addcylinder.AddCylinderScreen
import com.tripleenergytech.recordrefill.presentation.managecylinders.ManageCylindersScreen
import com.tripleenergytech.recordrefill.presentation.refillhome.RefillHomeScreen
import com.tripleenergytech.recordrefill.presentation.refillhome.SplashScreen
import com.tripleenergytech.recordrefill.presentation.refillrecord.RefillRecordScreen
import com.tripleenergytech.recordrefill.presentation.summary.SummaryScreen
import com.tripleenergytech.recordrefill.ui.theme.RecordRefillTheme
import com.tripleenergytech.recordrefill.ui.theme.greyColor
import com.tripleenergytech.recordrefill.ui.theme.primaryColor
import com.tripleenergytech.recordrefill.ui.theme.secondaryColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordRefillTheme {

                Column(Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(title: String) {
    TopAppBar(
        title = { Text(modifier = Modifier.fillMaxWidth(), text = title, color = Color.White) },
        colors = topAppBarColors(
        primaryColor
        )
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.shadow(elevation = NavigationBarDefaults.Elevation), containerColor = greyColor) {
                Utils.bottomNavItems.forEach{item->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route){

                               if (item.route != Utils.NavigationRoutes.SPLASH_SCREEN){
                                   popUpTo(item.route) {
                                       inclusive = true
                                   }
                                }

                            }

                          },
                        label = {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon",
                            )
                        }
                    )

                }
            }
        },
        content = {
            NavHost(modifier = Modifier.padding(bottom = 80.dp),navController = navController, startDestination = Screen.RefillHomeScreen.route){
                composable(Screen.RefillRecordScreen.route

                    ) {
                    RefillRecordScreen(navController=navController)
                }

                composable(Screen.SplashScreen.route
                ) {
                   SplashScreen(navController=navController)
                }

                composable(
                    Screen.AddCylinderScreen.route
                ) {
                    AddCylinderScreen(navController = navController)
                }
                composable(
                    Screen.ManagerCylinderScreen.route
                ) {
                    ManageCylindersScreen(navController = navController)
                }
                composable(
                    Screen.RefillHomeScreen.route
                ) {
                    RefillHomeScreen(navController = navController)
                }

                composable(
                    Screen.SummaryScreen.route
                ) {
                    SummaryScreen(navController = navController)
                }

                composable(
                    Screen.ManagerCylinderScreen.route
                ) {
                    ManageCylindersScreen(navController = navController)
                }


            }
        }
    )

}


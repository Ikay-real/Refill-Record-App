package com.tripleenergytech.recordrefill

sealed class Screen(val route:String) {
    object RefillRecordScreen: Screen("refill_record_screen")
    object AddCylinderScreen: Screen("add_cylinder_screen")
    object ManagerCylinderScreen: Screen("manage_cylinders_screen")
    object RefillHomeScreen: Screen("refill_home_screen")
    object SummaryScreen: Screen("summary_screen")
    object SplashScreen: Screen("splash_screen")

}


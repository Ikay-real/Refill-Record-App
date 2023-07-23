package com.tripleenergytech.recordrefill.common

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.unit.sp
import com.tripleenergytech.recordrefill.common.models.misc.BottomNavItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {
    val descriptions = mutableMapOf(
        "des_cylinder_image" to "Add cylinder"
    )
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "refill_home_screen",
            icon = Icons.Rounded.Home,
        ),
        BottomNavItem(
            name = "Summary",
            route = "summary_screen",
            icon = Icons.Rounded.Home,
        ),
        BottomNavItem(
            name = "Settings",
            route = "refill_home_screen",
            icon = Icons.Rounded.Settings,
        ),
    )
   object NavigationRoutes{
       const val MANAGE_CYLINDERS ="manage_cylinders_screen"
       const val ADD_CYLINDERS ="add_cylinder_screen"
       const val SPLASH_SCREEN ="splash_screen"
       const val REFILL_HOME_SCREEN ="refill_home_screen"
   }

    object TextSize {
        val textSmallest = 11.sp
        val textSmall = 15.sp
        val textMedium = 18.sp
        val textBig = 22.sp
    }

    object Tools {
        fun generateRandomString(length: Int): String {
            val chars = ('A'..'Z') + ('0'..'9')
            return (1..length)
                .map { chars.random() }
                .joinToString("")
        }


        class RandomIDGenerator {
            companion object {
                enum class Type {
                    REFILL_RECORD,
                    CYLINDER_RECORD,
                    DAY_SESSION_RECORD_ID,
                    CARRY_OVER_SESSION_ID,
                    ACTIVE_CYLINDER_RECORD_ID
                }

                fun generateID(idType: Type): String {
                    val chars = ('A'..'Z') + ('0'..'9')
                    val id = (1..18)
                        .map { chars.random() }
                        .joinToString("")
                    return when (idType) {
                        Type.REFILL_RECORD -> "RR_$id"
                        Type.CYLINDER_RECORD -> "CR_$id"
                        Type.DAY_SESSION_RECORD_ID -> "DSR_$id"
                        Type.CARRY_OVER_SESSION_ID-> "CS_$id"
                        Type.ACTIVE_CYLINDER_RECORD_ID->"ACR_$id"
                    }
                }
            }
        }

        object DateFormatter{
            fun getCurrentDateFormattedInBritishStandard(): String {
                val currentDate = Calendar.getInstance()
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
                return formatter.format(currentDate.time)
            }
            @SuppressLint("SimpleDateFormat")
            fun convertLongToDate(longValue: Long): String {
                val date = Date(longValue)
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                return sdf.format(date)
            }


        }
    }



    ///MISC

    enum class SButtonChoice{
        ADD_RECORD_REFILL,
        ADD_DAY_SESSION,
    }

    //Listeners
    interface GetValueCallback {
        fun onValueGet(value: String)
    }

}
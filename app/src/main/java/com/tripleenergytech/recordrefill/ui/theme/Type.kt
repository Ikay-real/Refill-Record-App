package com.tripleenergytech.recordrefill.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tripleenergytech.recordrefill.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Mxedium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */


)

val exo2 = FontFamily(
    Font(R.font.exotwo_regular,FontWeight.Normal),
    Font(R.font.exotwo_light,FontWeight.Light),
    Font(R.font.exotwo_bold,FontWeight.Bold),
    Font(R.font.exotwo_semibold,FontWeight.SemiBold)
)


val appTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),

    // Display Medium - Montserrat 45/52 . 0px
    displayMedium = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),

    // Display Small - Montserrat 36/44 . 0px
    displaySmall = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
    ),

    // Headline Large - Montserrat 32/40 . 0px
    headlineLarge = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
    ),

    // Headline Medium - Montserrat 28/36 . 0px
    headlineMedium = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    ),

    // Headline Small - Montserrat 24/32 . 0px
    headlineSmall = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    // Title Large - Montserrat 22/28 . 0px
    titleLarge = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),

    // Title Medium - Montserrat 16/24 . 0.15px
    titleMedium = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),

    // Title Small - Montserrat 14/20 . 0.1px
    titleSmall = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    // Label Large - Montserrat 14/20 . 0.1px
    labelLarge = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    // Label Medium - Montserrat 12/16 . 0.5px
    labelMedium = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),

    // Label Small - Montserrat 11/16 . 0.5px
    labelSmall = TextStyle(
        fontFamily = exo2,
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)
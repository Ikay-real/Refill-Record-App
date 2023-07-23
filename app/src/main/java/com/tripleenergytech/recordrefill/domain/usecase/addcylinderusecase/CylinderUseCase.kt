package com.tripleenergytech.recordrefill.domain.usecase.addcylinderusecase

import androidx.compose.ui.text.font.FontWeight

interface CylinderUseCase {
    suspend fun addCylinder(
        cylinderName:String,
        tareWeight: Float,
        tare_and_gasWeight:Float,
        gasWeight: Float
    )
}
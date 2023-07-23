package com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase

import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import kotlinx.coroutines.flow.Flow

interface ManageCylinderUseCase {
    suspend fun getAllActiveCylinders(): Flow<List<ActiveCylinder>>
    suspend fun getActiveCylinder(activeCylinderId:String):Flow<ActiveCylinder>
    suspend fun getTotalGasWeight():Float
}
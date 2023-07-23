package com.tripleenergytech.recordrefill.domain.repository

import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import kotlinx.coroutines.flow.Flow


interface CylinderRepository {
    fun getCylinder(): Flow<List<Cylinder>>
    suspend fun addCylinder(cylinder: Cylinder)
    suspend fun updateCylinder(cylinder: Cylinder)
    suspend fun deleteCylinder(cylinder: Cylinder)


    suspend fun getAllActiveCylinders():Flow<List<ActiveCylinder>>
    suspend fun addActiveCylinder(activeCylinder: ActiveCylinder)
    suspend fun updateActiveCylinder(activeCylinder: ActiveCylinder)
    suspend fun getActiveCylinder(activeCylinderId:String):Flow<ActiveCylinder>
    suspend fun getTotalGasWeight():Float
}
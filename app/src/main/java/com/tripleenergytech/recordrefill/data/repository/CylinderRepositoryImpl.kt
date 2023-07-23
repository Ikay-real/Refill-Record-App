package com.tripleenergytech.recordrefill.data.repository

import com.tripleenergytech.recordrefill.data.local.dao.CylinderDao
import com.tripleenergytech.recordrefill.data.local.dao.RefillRecordDao
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import com.tripleenergytech.recordrefill.domain.repository.CylinderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CylinderRepositoryImpl @Inject constructor(private val cylinderDao: CylinderDao):CylinderRepository {
    override fun getCylinder(): Flow<List<Cylinder>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCylinder(cylinder: Cylinder) {
        cylinderDao.addCylinder(cylinder = cylinder)
    }

    override suspend fun updateCylinder(cylinder: Cylinder) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCylinder(cylinder: Cylinder) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllActiveCylinders() :Flow<List<ActiveCylinder>> {
        return cylinderDao.getAllActiveCylinders()
    }
    override suspend fun addActiveCylinder(activeCylinder: ActiveCylinder) {
        cylinderDao.addActiveCylinder(activeCylinder)
    }

    override suspend fun updateActiveCylinder(activeCylinder: ActiveCylinder) {
        cylinderDao.updateActiveCylinder(activeCylinder)
    }

    override suspend fun getActiveCylinder(activeCylinderId: String): Flow<ActiveCylinder> {
        return cylinderDao.getActiveCylinder(activeCylinderId = activeCylinderId)
    }

    override suspend fun getTotalGasWeight(): Float {
        TODO("Not yet implemented")
    }
}
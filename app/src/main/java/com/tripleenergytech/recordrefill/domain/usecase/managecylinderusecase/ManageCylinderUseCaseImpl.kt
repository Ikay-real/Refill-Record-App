package com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase

import com.tripleenergytech.recordrefill.data.repository.CylinderRepositoryImpl
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import com.tripleenergytech.recordrefill.domain.repository.CylinderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageCylinderUseCaseImpl @Inject constructor(private val cylinderRepository: CylinderRepository):ManageCylinderUseCase {
    override suspend fun getAllActiveCylinders(): Flow<List<ActiveCylinder>> {
        return cylinderRepository.getAllActiveCylinders()
    }
    override suspend fun getActiveCylinder(activeCylinderId:String):Flow<ActiveCylinder>{
        return cylinderRepository.getActiveCylinder(activeCylinderId = activeCylinderId)
    }

    override suspend fun getTotalGasWeight(): Float {
        TODO("Not yet implemented")
    }
}
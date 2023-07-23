package com.tripleenergytech.recordrefill.domain.usecase.summaryusecase

import com.tripleenergytech.recordrefill.data.repository.CylinderRepositoryImpl
import com.tripleenergytech.recordrefill.data.repository.RefillRecordRepositoryImpl
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummaryUseCaseImpl@Inject constructor(
    private val cylinderRepository: CylinderRepositoryImpl,
    private val recordRefillRepositoryImpl:RefillRecordRepositoryImpl
) {
    suspend fun getAllDaySession(): Flow<List<DaySession>> {
        return recordRefillRepositoryImpl.getAllDaySessions()
    }

    suspend fun getAllActiveCylinders(): Flow<List<ActiveCylinder>> {
        return cylinderRepository.getAllActiveCylinders()
    }
}
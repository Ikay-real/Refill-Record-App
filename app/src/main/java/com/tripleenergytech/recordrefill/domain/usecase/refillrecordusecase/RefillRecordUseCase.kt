package com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase

import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import kotlinx.coroutines.flow.Flow

interface RefillRecordUseCase {
    suspend fun addRefillRecord(
        cylinderId: String,
        recordName: String,
        tareWeight: Float,
        gasWeight: Float,
    )
    suspend fun createDaySession()
    suspend fun getDaySession(): DaySession
    suspend fun getRefillRecords(): Flow<List<RefillRecord>>
}
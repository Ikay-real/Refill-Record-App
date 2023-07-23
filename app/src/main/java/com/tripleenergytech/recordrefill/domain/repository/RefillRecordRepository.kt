package com.tripleenergytech.recordrefill.domain.repository

import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import kotlinx.coroutines.flow.Flow


interface RefillRecordRepository {
    fun getRefillRecord(): Flow<List<RefillRecord>>
    suspend fun addRefillRecord(refillRecord: RefillRecord)
    suspend fun updateRefillRecord(refillRecord: RefillRecord)
    suspend fun deleteRefillRecord(refillRecord: RefillRecord)
}
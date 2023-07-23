package com.tripleenergytech.recordrefill.domain.repository

import com.tripleenergytech.recordrefill.domain.model.DaySession
import kotlinx.coroutines.flow.Flow

interface RefillRecordGenericRepository {
    suspend fun getDaySession():DaySession
    suspend fun createDaySession(daySession: DaySession)
    suspend fun getAllDaySessions():Flow<List<DaySession>>
}
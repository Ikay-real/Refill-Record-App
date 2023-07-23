package com.tripleenergytech.recordrefill.data.local.dao

import androidx.room.*
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RefillRecordDao{
    @Query("SELECT * FROM refill_records")
    fun getRefillRecords(): Flow<List<RefillRecord>>

    @Insert
    suspend fun addRefillRecord(refillRecord: RefillRecord)

    @Update
    suspend fun updateRefillRecord(refillRecord: RefillRecord)

    @Delete
    suspend fun deleteRefillRecord(refillRecord: RefillRecord)
}
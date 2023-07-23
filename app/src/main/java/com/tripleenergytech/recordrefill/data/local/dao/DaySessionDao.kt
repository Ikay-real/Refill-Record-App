package com.tripleenergytech.recordrefill.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tripleenergytech.recordrefill.domain.model.DaySession
import kotlinx.coroutines.flow.Flow

@Dao
interface DaySessionDao {
    @Insert
    fun createDaySession(daySession: DaySession)
    @Query("SELECT * FROM day_sessions WHERE closed =0")
    fun getDaySession(): DaySession

    @Query("SELECT * FROM day_sessions")
    fun getAllDaySessions():Flow<List<DaySession>>

}
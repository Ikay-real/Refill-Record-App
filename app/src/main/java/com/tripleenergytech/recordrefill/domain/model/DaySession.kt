package com.tripleenergytech.recordrefill.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_sessions")
class DaySession (
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val day_session_id:String,
    val start_timestamp:Long,
    val close_timestamp:Long?,
    val closed:Int=0
)
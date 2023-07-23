package com.tripleenergytech.recordrefill.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "refill_records")
data class RefillRecord(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val refill_record_id:String,
    val name:String,
    val tare_weight:Float,
    val gas_weight:Float,
    val tare_and_gas_weight:Float,
    val mid_value:Float,
    val mid_value_gas:Float,
    val timestamp:Long,
    val cylinder_id:String,
    val carry_over_session_id:String,
    val carry_over_done:Int
)
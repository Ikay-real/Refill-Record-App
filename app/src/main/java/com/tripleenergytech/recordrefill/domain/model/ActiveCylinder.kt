package com.tripleenergytech.recordrefill.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "active_cylinders")
data class ActiveCylinder(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val active_cylinder_id:String,
    val cylinder_id:String,
    val cylinder_name:String,
    val tare_weight:Float,
    val tare_and_gas_weight:Float,
    val gas_weight:Float,
    val timestamp_start: Long,
    val timestamp_closed: Long?,
    val closed:Int=0
)
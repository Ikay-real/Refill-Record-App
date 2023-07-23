package com.tripleenergytech.recordrefill.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "cylinders")
data class Cylinder(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,
    val cylinder_id:String,
    val cylinder_name:String,
    val tare_mass:Float,
    val tare_and_gas_mass:Float,
    val gas_amount:Float,
    val timestamp:Long,
)
package com.tripleenergytech.recordrefill.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val user_id:String,
    val user_type:String,
    val name:String,
    val birthday:Long,
    val address:String,
    val email:String,
    val phone:String,
    val password:String,
    val acc_created:Long,
    val acc_is_active:Int
)
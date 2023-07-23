package com.tripleenergytech.recordrefill.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tripleenergytech.recordrefill.data.local.dao.CylinderDao
import com.tripleenergytech.recordrefill.data.local.dao.DaySessionDao
import com.tripleenergytech.recordrefill.data.local.dao.RefillRecordDao
import com.tripleenergytech.recordrefill.data.local.dao.UserDao
import com.tripleenergytech.recordrefill.domain.model.*

@Database(entities = [Cylinder::class, RefillRecord::class, User::class, ActiveCylinder::class, DaySession::class], version = 1,exportSchema = false)
abstract class RefillRecordDatabase: RoomDatabase() {
    abstract fun cylinderDao():CylinderDao
    abstract fun userDao(): UserDao
    abstract fun refillRecordDao():RefillRecordDao
    abstract fun daySessionDao():DaySessionDao
}
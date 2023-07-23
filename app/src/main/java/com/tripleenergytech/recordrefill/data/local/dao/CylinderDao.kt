package com.tripleenergytech.recordrefill.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface CylinderDao {
    @Query("SELECT * FROM cylinders")
    fun getAllCylinders(): Flow<List<Cylinder>>
    @Insert
    suspend fun addCylinder(cylinder: Cylinder)

    @Update
    suspend fun updateCylinder(cylinder: Cylinder)

    @Delete
    suspend fun deleteCylinder(cylinder: Cylinder)




    @Query("SELECT * FROM active_cylinders")
    fun getAllActiveCylinders(): Flow<List<ActiveCylinder>>

    @Insert
    suspend fun addActiveCylinder(cylinder: ActiveCylinder)

    @Update
    suspend fun updateActiveCylinder(cylinder: ActiveCylinder)

    @Query("SELECT * FROM active_cylinders WHERE active_cylinder_id = :activeCylinderId")
    fun getActiveCylinder(activeCylinderId:String): Flow<ActiveCylinder>

    @Query("SELECT COUNT(gas_weight) FROM active_cylinders")
    fun getTotalGasWeight(): Float

}
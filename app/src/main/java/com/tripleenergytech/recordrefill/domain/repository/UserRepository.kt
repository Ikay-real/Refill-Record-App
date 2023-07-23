package com.tripleenergytech.recordrefill.domain.repository

import androidx.room.Dao
import com.tripleenergytech.recordrefill.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}
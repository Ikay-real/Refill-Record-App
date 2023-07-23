package com.tripleenergytech.recordrefill.data.repository

import com.tripleenergytech.recordrefill.domain.model.User
import com.tripleenergytech.recordrefill.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl: UserRepository {
    override fun getUsers(): Flow<List<User>> =flow {

    }

    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}
package com.tripleenergytech.recordrefill.data.repository

import com.tripleenergytech.recordrefill.data.local.dao.DaySessionDao
import com.tripleenergytech.recordrefill.data.local.dao.RefillRecordDao
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordGenericRepository
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefillRecordRepositoryImpl @Inject constructor(private val refillRecordDao: RefillRecordDao,private val daySessionDao: DaySessionDao): RefillRecordRepository,RefillRecordGenericRepository {

    override fun getRefillRecord(): Flow<List<RefillRecord>> {

        return refillRecordDao.getRefillRecords()
    }

    override suspend fun addRefillRecord(refillRecord: RefillRecord) {
        refillRecordDao.addRefillRecord(refillRecord = refillRecord)
    }

    override suspend fun updateRefillRecord(refillRecord: RefillRecord) {
        refillRecordDao.updateRefillRecord(refillRecord = refillRecord)
    }

    override suspend fun deleteRefillRecord(refillRecord: RefillRecord) {
        refillRecordDao.deleteRefillRecord(refillRecord = refillRecord)
    }

    override suspend fun getDaySession(): DaySession {
        return daySessionDao.getDaySession()
    }

    override suspend fun createDaySession(daySession: DaySession) {
        daySessionDao.createDaySession(daySession)
    }

    override suspend fun getAllDaySessions(): Flow<List<DaySession>> {
        return daySessionDao.getAllDaySessions()
    }


    //Day session


}
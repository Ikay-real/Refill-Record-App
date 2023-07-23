package com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase

import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.data.repository.CylinderRepositoryImpl
import com.tripleenergytech.recordrefill.data.repository.RefillRecordRepositoryImpl
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefillRecordUseCaseImpl @Inject constructor(private val refillRecordRepository: RefillRecordRepositoryImpl):RefillRecordUseCase {
    private lateinit var refillRecordId:String

     override suspend fun addRefillRecord(
        cylinderId: String,
        recordName: String,
        tareWeight: Float,
        gasWeight: Float
    ) {
        refillRecordId =Utils.Tools.RandomIDGenerator.generateID(Utils.Tools.RandomIDGenerator.Companion.Type.REFILL_RECORD)
        //this is just inserting
        val refillRecord =RefillRecord(
            refill_record_id = refillRecordId,
            name = recordName,
            tare_weight = tareWeight,
            gas_weight = gasWeight,
            tare_and_gas_weight = tareWeight+gasWeight,
            mid_value = 0.0f,
            mid_value_gas = 0.0f,
            timestamp = System.currentTimeMillis(),
            cylinder_id =  cylinderId,
            carry_over_session_id = "",
            carry_over_done = 0
        )
        refillRecordRepository.addRefillRecord(refillRecord = refillRecord)

        //refillRecordRepository.addRefillRecord() id,rr_id,total_gas_tare,mid_val,mid_val_gas,timestamp,carry over,carry over done
    }

    override suspend fun createDaySession(){
        val daySessionId = Utils.Tools.RandomIDGenerator.generateID(Utils.Tools.RandomIDGenerator.Companion.Type.DAY_SESSION_RECORD_ID)
        val daySession = DaySession(
            day_session_id = daySessionId,
            start_timestamp = System.currentTimeMillis(),
            close_timestamp = null,
            closed = 0
        )

        refillRecordRepository.createDaySession(daySession)
    }

    override suspend fun getDaySession(): DaySession {
        return refillRecordRepository.getDaySession()
    }

    override suspend fun getRefillRecords(): Flow<List<RefillRecord>> {
        return refillRecordRepository.getRefillRecord()
    }



}
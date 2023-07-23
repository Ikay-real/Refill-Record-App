package com.tripleenergytech.recordrefill.presentation.refillrecord

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCase
import com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase.RefillRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefillRecordScreenViewModel @Inject constructor(
    private val refillRecordUseCase: RefillRecordUseCase,
    private val manageCylinderUseCase: ManageCylinderUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<RefillRecordState>(RefillRecordState.EmptyState)
    val state: StateFlow<RefillRecordState> = _state.asStateFlow()
    lateinit var daySession: DaySession


    private val _refillRecords = MutableStateFlow<List<RefillRecord>>(emptyList())
    val record: StateFlow<List<RefillRecord>> get() =_refillRecords

    private val _activeCylinderRecords = MutableStateFlow<List<ActiveCylinder>>(emptyList())
    val activeCylinderRecords: StateFlow<List<ActiveCylinder>> get() =_activeCylinderRecords

    private val _totalGasAvailable=  MutableStateFlow(0.0f)
    val totalGasValue: StateFlow<Float> get() = _totalGasAvailable



    //ViewModel value
    var cylinderIdViewModel: String? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {


            /*try {
                val ds = refillRecordUseCase.getDaySession()
                ds.apply {
                    Log.e("Day sessoin", "$id $start_timestamp $day_session_id")
                }
            } catch (npe: NullPointerException) {
                _state.value = RefillRecordState.Error("Please open day session")
                Log.e("Error ", "${npe.message} Create a day session")
            }*/
            //refillRecordUseCase.createDaySession()
            getAllActiveCylinders()

        }
    }

    fun addRefillRecord(
        cylinderId: String,
        recordName: String,
        tareWeight: Float,
        gasWeight: Float,
    ) {
        viewModelScope.launch {
            try {
                refillRecordUseCase.addRefillRecord(
                    cylinderId,
                    recordName,
                    tareWeight,
                    gasWeight
                )
                _state.value = RefillRecordState.Regular("Record added")
                refreshRefillRecord()
            } catch (e: Exception) {
                Log.e("Add record in db", "Did not enter in db")
                _state.value = RefillRecordState.Error("Technical Error cant add record")
            }
        }
    }
    fun setCylinderId(id: String) {
        cylinderIdViewModel = id
    }
    fun changeState(){
        if(state.value == RefillRecordState.EmptyState){
            _state.value = RefillRecordState.Error("efywe")

        }else{
            _state.value = RefillRecordState.EmptyState
        }
    }
    private fun refreshRefillRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                refillRecordUseCase.getRefillRecords().collect{records->
                    Log.e("SUCCESS","got the records")
                        //_refillRecords.value =records

                    records.forEach{
                        Log.e("REC item","$it")
                    }
                }
            }catch (e:Exception){
                Log.e("ERROR ","${e.message} didn't get  the records")
            }
        }
    }
    fun getAllActiveCylinders(){
        Log.e("GAS TOTAL", " Yah")
        viewModelScope.launch {
            manageCylinderUseCase.getAllActiveCylinders().collect{record->
                _activeCylinderRecords.value = record
                _totalGasAvailable.value = record.sumOf{ it.gas_weight.toDouble() }.toFloat()
            }
        }
    }

}
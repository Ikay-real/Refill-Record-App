package com.tripleenergytech.recordrefill.presentation.refillhome

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCase
import com.tripleenergytech.recordrefill.domain.usecase.refillrecordusecase.RefillRecordUseCase
import com.tripleenergytech.recordrefill.presentation.refillhome.states.DaySessionState
import com.tripleenergytech.recordrefill.presentation.refillrecord.RefillRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefillHomeViewModel @Inject constructor(
    private val refillRecordUseCase: RefillRecordUseCase,
    private val manageCylinderUseCase: ManageCylinderUseCase
) : ViewModel() {
    private var _daySessionState = MutableStateFlow<DaySessionState>(DaySessionState.Empty)
    val daySessionState: StateFlow<DaySessionState> = _daySessionState
    var daySession: DaySession? = null
    private var _state = MutableStateFlow(RefillHomeState())
    val state: StateFlow<RefillHomeState> = _state

    private val _refillRecords = MutableStateFlow<List<RefillRecord>>(emptyList())
    val refillRecords:MutableStateFlow<List<RefillRecord>> get() =_refillRecords



    private var _activeCylinderState = MutableStateFlow<ActiveCylinderState>(ActiveCylinderState.Empty)
    val activeCylinderState: StateFlow<ActiveCylinderState> = _activeCylinderState

    private val _activeCylinder = MutableStateFlow<ActiveCylinder?>(null)
    val activeCylinder: StateFlow<ActiveCylinder?> get() = _activeCylinder

    fun createDaySession() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                refillRecordUseCase.createDaySession()
                daySession=refillRecordUseCase.getDaySession()
                _daySessionState.emit(DaySessionState.Present(data = daySession!!))
                Log.e("success","Record added")
            }catch (e:Exception){
                Log.e("Exception","${e.message}")
                _daySessionState.emit(DaySessionState.Error("Database error"))
            }

        }
    }


    fun getDaySession() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                daySession = refillRecordUseCase.getDaySession()
                _daySessionState.emit(DaySessionState.Present(data = daySession!!))
            }catch (e:Exception){
                daySession = refillRecordUseCase.getDaySession()
                _daySessionState.emit(DaySessionState.Error(""))
            }

        }
    }

    fun getRefillRecords() {
        viewModelScope.launch {

            refillRecordUseCase.getRefillRecords().collect { records->
                _refillRecords.value = records
            }
        }
    }

    fun getInitialDateSession(): DaySession? {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                daySession = refillRecordUseCase.getDaySession()
                _daySessionState.emit(DaySessionState.Present(data = daySession!!))
                Log.e("Try worked", daySession!!.day_session_id)
            }
            catch(e:Exception){
                Log.e("excep","${e.message}")
            }
        }
        daySession?.let {
            Log.e("day session----","it")
        }
        return daySession
    }

    fun getActiveCylinder(activeCylinderId:String){
        viewModelScope.launch {
            manageCylinderUseCase.getActiveCylinder(activeCylinderId = activeCylinderId).collect{
                _activeCylinder.value = it
            }
        }
    }


    companion object{
        data class RefillHomeState(
            val empty: Boolean = false,
            val present: DaySession? = null,
            val error: String = ""
        )
    }


    sealed class ActiveCylinderState {
        object Empty : ActiveCylinderState()
        data class Present(val data: ActiveCylinder) : ActiveCylinderState()
        data class Error(val data: String) : ActiveCylinderState()
    }
}


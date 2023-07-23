package com.tripleenergytech.recordrefill.presentation.summary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.DaySession
import com.tripleenergytech.recordrefill.domain.usecase.summaryusecase.SummaryUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(private val summaryUseCaseImpl: SummaryUseCaseImpl) : ViewModel() {
    private val _activeCylinderRecords = MutableStateFlow<List<ActiveCylinder>>(emptyList())
    val activeCylinderRecords: MutableStateFlow<List<ActiveCylinder>> get() =_activeCylinderRecords
    val gasCylinderCount: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _daySessions=MutableStateFlow<List<DaySession>>(emptyList())
    val daySessions: StateFlow<List<DaySession>> get() = _daySessions
    init {
        Log.e("1234","hie hie")
        viewModelScope.launch {
            Log.e("hjgnr","rtujrtjuityg")
           summaryUseCaseImpl.getAllActiveCylinders().collect{records->
               gasCylinderCount.value = records.size
                _activeCylinderRecords.value = records
            }
        }
        getAllDaySession()


    }
    private fun getAllDaySession(){
        viewModelScope.launch{
            summaryUseCaseImpl.getAllDaySession().collect{records->
                _daySessions.value = records
            }
        }
    }

}
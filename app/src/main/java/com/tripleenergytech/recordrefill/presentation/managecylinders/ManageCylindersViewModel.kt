package com.tripleenergytech.recordrefill.presentation.managecylinders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.RefillRecord
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCase
import com.tripleenergytech.recordrefill.domain.usecase.managecylinderusecase.ManageCylinderUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageCylindersViewModel@Inject constructor(manageCylinderUseCase: ManageCylinderUseCaseImpl) :ViewModel(){
    private val _activeCylinderRecords = MutableStateFlow<List<ActiveCylinder>>(emptyList())
    val activeCylinderRecords:MutableStateFlow<List<ActiveCylinder>> get() =_activeCylinderRecords


    init {
        viewModelScope.launch {
            Log.e("hjgnr","rtujrtjuityg")
            manageCylinderUseCase.getAllActiveCylinders().collect{records->
                _activeCylinderRecords.value = records
            }
        }
    }

}
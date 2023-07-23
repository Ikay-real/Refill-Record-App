package com.tripleenergytech.recordrefill.presentation.addcylinder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripleenergytech.recordrefill.domain.repository.CylinderRepository
import com.tripleenergytech.recordrefill.domain.repository.RefillRecordRepository
import com.tripleenergytech.recordrefill.domain.usecase.addcylinderusecase.CylinderUseCase
import com.tripleenergytech.recordrefill.presentation.refillrecord.RefillRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCylinderScreenViewModel @Inject constructor(
    private val cylinderUseCase: CylinderUseCase
    ):ViewModel(){
    private val _state = MutableStateFlow<AddCylinderState>(AddCylinderState.Regular)
    val state: StateFlow<AddCylinderState> = _state
    fun addCylinder(
        cylinderName:String,
        tareWeight: Float,
        tare_and_gasWeight:Float,
        gasWeight: Float
    ){
        viewModelScope.launch {
            try {
                when(_state.value){
                    AddCylinderState.Regular->{
                        cylinderUseCase.addCylinder(
                            cylinderName,
                            tareWeight,
                            tare_and_gasWeight,
                            gasWeight
                        )
                        Log.e("Cylinder add","record added")
                    }
                    AddCylinderState.Error ->{
                        _state.value=AddCylinderState.Regular
                        Log.e("Cylinder add","record error")
                    }
                }


            }catch (e:Exception){
                Log.e("Cylinder add","record error")
            }

        }

    }
}
package com.tripleenergytech.recordrefill.presentation.addcylinder

import com.tripleenergytech.recordrefill.presentation.refillrecord.RefillRecordState

sealed class AddCylinderState {
    object Regular : AddCylinderState()
    object Error:AddCylinderState()
}
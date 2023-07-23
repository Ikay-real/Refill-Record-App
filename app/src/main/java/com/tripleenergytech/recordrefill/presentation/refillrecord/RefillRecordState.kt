    package com.tripleenergytech.recordrefill.presentation.refillrecord

sealed class RefillRecordStateeee {
    object Regular : RefillRecordState()
    object CarryOver : RefillRecordState()
    object Error:RefillRecordState()
}

sealed class RefillRecordState {
    data class Regular(val data:String) : RefillRecordState()
    data class CarryOver<T>(val data: T) : RefillRecordState()
    data class Error(val message: String) : RefillRecordState()
    object EmptyState : RefillRecordState()
}


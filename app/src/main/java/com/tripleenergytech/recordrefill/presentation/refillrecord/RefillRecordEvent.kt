package com.tripleenergytech.recordrefill.presentation.refillrecord


sealed interface RefillRecordEvent {
    object GetValue : RefillRecordEvent
}
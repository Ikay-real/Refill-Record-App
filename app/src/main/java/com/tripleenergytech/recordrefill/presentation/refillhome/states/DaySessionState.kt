package com.tripleenergytech.recordrefill.presentation.refillhome.states

import com.tripleenergytech.recordrefill.domain.model.DaySession

sealed class DaySessionState {
    object Empty : DaySessionState()
    data class Present(val data: DaySession) : DaySessionState()
    data class Error(val data: String) : DaySessionState()
}
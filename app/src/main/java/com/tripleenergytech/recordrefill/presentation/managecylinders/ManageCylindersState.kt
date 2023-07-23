package com.tripleenergytech.recordrefill.presentation.managecylinders

import com.tripleenergytech.recordrefill.domain.model.DaySession

sealed class ManageCylindersState {
    object Empty : ManageCylindersState()
    data class Present(val data: DaySession) : ManageCylindersState()
    data class Error(val data: String) : ManageCylindersState()
}
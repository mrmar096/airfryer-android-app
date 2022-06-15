package com.mrmar.airfryer.core.presentation.viewmodel.components

interface ViewState {
    val isLoading: Boolean
    fun getErrorMessage(): String? = null
    fun clearErrors(): ViewState? = null
}

interface ViewEvent

interface ViewSideEffect
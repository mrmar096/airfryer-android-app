package com.mrmar.airfryer.core.presentation.viewmodel.components

interface ViewState {
    val isLoading: Boolean
    fun getErrorMessage(): String? = null
}

interface ViewEvent

interface ViewSideEffect
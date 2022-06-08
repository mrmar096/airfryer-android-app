package com.mrmar.airfryer.login.presentation.viewmodel

import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun navigateBack(){
        router.navigateBack()
    }
}
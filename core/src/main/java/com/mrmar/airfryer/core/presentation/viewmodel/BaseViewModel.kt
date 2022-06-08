package com.mrmar.airfryer.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mrmar.airfryer.core.presentation.router.Router
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    @Inject lateinit var router: Router
}
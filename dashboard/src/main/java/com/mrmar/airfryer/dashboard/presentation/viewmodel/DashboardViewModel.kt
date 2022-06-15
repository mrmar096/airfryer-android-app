package com.mrmar.airfryer.dashboard.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.dashboard.presentation.viewmodel.contract.DashboardContract
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.navigation.routes.LoginRouteError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val resources: Resources
) : BaseViewModel<
        DashboardContract.Event,
        DashboardContract.State,
        DashboardContract.Effect>(stateHandle) {


    override fun setInitialState(): DashboardContract.State {
        return DashboardContract.State(error = LoginRouteError.resolveArguments(stateHandle))
    }

    override fun handleEvents(event: DashboardContract.Event) {

    }

    private fun handleError(domainError: DomainError) {
        setState { copy(isLoading = false, error = domainError.getStringResource(resources)) }
    }
}
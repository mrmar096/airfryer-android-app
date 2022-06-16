package com.mrmar.airfryer.dashboard.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.dashboard.R
import com.mrmar.airfryer.dashboard.presentation.viewmodel.contract.DashboardContract
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.models.CookSetup
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal
import com.mrmar.airfryer.domain.repository.device.DeviceRepository
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryCoroutineHandler
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.navigation.routes.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val resources: Resources,
    private val loginRepository: LoginRepository,
    private val deviceRepository: DeviceRepository,
) : BaseViewModel<
        DashboardContract.Event,
        DashboardContract.State,
        DashboardContract.Effect>(stateHandle) {

    init {
        launch {
            val status = deviceRepository.getStatus()
            setState { copy(isLoading = false, deviceStatus = status) }
        }
    }

    override fun setInitialState(): DashboardContract.State {
        return DashboardContract.State(meals = mockMealList(), isLoading = true)
    }

    private fun mockMealList(): List<Meal> {
        return listOf(
            Meal(
                "Chicken", R.drawable.ic_chicken_leg, CookSetup(195, 20)
            ),
            Meal(
                "Bread", R.drawable.ic_bread, CookSetup(175, 10)
            ),
            Meal(
                "Chips", R.drawable.ic_fries, CookSetup(195, 25)
            ),
            Meal(
                "Fish", R.drawable.ic_fish, CookSetup(175, 8)
            )
        )
    }

    override fun handleEvents(event: DashboardContract.Event) {
        when (event) {
            DashboardContract.Event.Logout -> launch {
                loginRepository.doLogout()
                exitToLogin()
            }
            is DashboardContract.Event.MealSelectionChange -> setState {
                copy(
                    mealSelected = if (event.meal == state.mealSelected) null else event.meal,
                )
            }
            DashboardContract.Event.StartCooking -> {
                launch {
                    state.mealSelected?.let {
                        setState {
                            copy(deviceStatus = DeviceStatus.COOKING)
                        }
                        deviceRepository.startCooking(it)
                    }
                }
            }
            DashboardContract.Event.StopCooking -> launch {
                if (state.isCooking) {
                    setState {
                        copy(deviceStatus = DeviceStatus.ONLINE)
                    }
                    deviceRepository.finishCooking()
                }
            }
        }
    }

    private fun handleError(domainError: DomainError) {
        //TODO handle common error in base
        when (domainError) {
            is DomainError.SessionExpired -> exitToLogin()
            is DomainError.NoConnectionError -> {
                setState {
                    copy(
                        isLoading = false,
                        error = domainError.getStringResource(resources),
                        deviceStatus = DeviceStatus.OFFLINE
                    )
                }
            }
            else -> setState {
                copy(
                    isLoading = false,
                    error = domainError.getStringResource(resources),
                )
            }
        }
    }

    private fun exitToLogin() {
        router.navigate(LoginRoute())
    }

    private fun launch(invoke: suspend () -> Unit) {
        viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
            invoke()
        }
    }
}
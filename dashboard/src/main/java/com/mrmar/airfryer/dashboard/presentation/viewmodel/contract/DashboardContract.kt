package com.mrmar.airfryer.dashboard.presentation.viewmodel.contract

import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal

class DashboardContract {
    sealed class Event : ViewEvent {
        object Logout : Event()
        object StartCooking : Event()
        object StopCooking : Event()
        data class MealSelectionChange(val meal: Meal) : Event()
    }

    data class State(
        override val isLoading: Boolean = false,
        val meals: List<Meal> = listOf(),
        val mealSelected: Meal? = null,
        val deviceStatus: DeviceStatus = DeviceStatus.OFFLINE,
        val error: String? = null
    ) : ViewState {

        val isCooking = deviceStatus == DeviceStatus.COOKING

        override fun getErrorMessage() = error

        override fun clearErrors(): State {
            return copy(error = null)
        }
    }

    sealed class Effect : ViewSideEffect {

    }
}
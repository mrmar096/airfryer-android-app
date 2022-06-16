package com.mrmar.airfryer.dashboard.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import com.mrmar.airfryer.core.presentation.viewmodel.BaseViewModel
import com.mrmar.airfryer.dashboard.R
import com.mrmar.airfryer.dashboard.presentation.viewmodel.contract.DashboardContract
import com.mrmar.airfryer.domain.errors.DomainError
import com.mrmar.airfryer.domain.models.CookSetup
import com.mrmar.airfryer.domain.models.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val resources: Resources,
) : BaseViewModel<
        DashboardContract.Event,
        DashboardContract.State,
        DashboardContract.Effect>(stateHandle) {


    override fun setInitialState(): DashboardContract.State {
        return DashboardContract.State(meals = mockMealList())
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
            DashboardContract.Event.Logout -> TODO()
            is DashboardContract.Event.MealSelectionChange -> setState {
                copy(mealSelected = if (event.meal == state.mealSelected) null else event.meal)
            }
            DashboardContract.Event.StartCooking -> setState {
                copy(isCooking = true)
            }
            DashboardContract.Event.StopCooking -> setState {
                copy(isCooking = false)
            }
        }
    }

    private fun handleError(domainError: DomainError) {
        setState { copy(isLoading = false, error = domainError.getStringResource(resources)) }
    }
}
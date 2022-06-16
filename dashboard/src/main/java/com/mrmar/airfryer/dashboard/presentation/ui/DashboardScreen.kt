package com.mrmar.airfryer.dashboard.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrmar.airfryer.core.ui.composables.AppBarScreen
import com.mrmar.airfryer.core.ui.composables.ExpandableProgressFloatingActionButton
import com.mrmar.airfryer.core.ui.composables.ImageChip
import com.mrmar.airfryer.core.ui.composables.LoadingScreen
import com.mrmar.airfryer.core.ui.theme.Inactive
import com.mrmar.airfryer.core.ui.theme.Negative400
import com.mrmar.airfryer.core.ui.theme.Shapes
import com.mrmar.airfryer.core.ui.theme.Teal200
import com.mrmar.airfryer.dashboard.R
import com.mrmar.airfryer.dashboard.presentation.viewmodel.DashboardViewModel
import com.mrmar.airfryer.dashboard.presentation.viewmodel.contract.DashboardContract
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal
import kotlin.text.Typography.bullet

private const val DASHBOARD_KEY_EFFECTS = "dashboard_effects"

@Composable
fun DashboardScreen() {
    val viewModel = hiltViewModel<DashboardViewModel>()
    DashboardContentBuilder(viewModel)
}

@Composable
private fun DashboardContentBuilder(viewModel: DashboardViewModel) {
    val state = viewModel.viewState.value
    LaunchedEffect(DASHBOARD_KEY_EFFECTS) {
        viewModel.effect.collect {

        }
    }
    LoadingScreen(isLoading = state.isLoading) {
        DashboardContent(state, viewModel::setEvent)
    }
}

@Composable
fun DashboardContent(
    state: DashboardContract.State,
    onEventSent: (event: DashboardContract.Event) -> Unit
) {
    val (cookingEvent, cookingText) = if (state.isCooking) {
        DashboardContract.Event.StopCooking to ""
    } else {
        DashboardContract.Event.StartCooking to stringResource(R.string.start_cooking)
    }
    val actionButtonColor = when {
        state.isCooking -> Negative400
        state.mealSelected == null -> Inactive
        else -> Teal200
    }
    AppBarScreen(
        stringResource(R.string.dashboard_title),
        logoutButton = { onEventSent(DashboardContract.Event.Logout) },
        floatingActionButton = {
            ExpandableProgressFloatingActionButton(
                cookingText,
                actionButtonColor,
                state.isCooking,
                state.mealSelected != null
            ) {
                onEventSent(cookingEvent)
            }
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            DeviceHeaderCard(
                state.meals,
                state.mealSelected,
                state.deviceStatus
            ) { onEventSent(DashboardContract.Event.MealSelectionChange(it)) }
        }
    }
}

@Composable
fun DeviceHeaderCard(
    meals: List<Meal>,
    selectedMeal: Meal? = null,
    deviceStatus: DeviceStatus,
    onSelectedChanged: (Meal) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
                .padding(horizontal = 24.dp, vertical = 40.dp),
            elevation = 8.dp,
            shape = Shapes.medium
        ) {
            DeviceCardContent(meals, selectedMeal, deviceStatus, onSelectedChanged)
        }
        DeviceCircleImage(deviceStatus)
    }
}

@Composable
fun DeviceCircleImage(deviceStatus: DeviceStatus) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            4.dp,
            color = Color(deviceStatus.getColorResource(LocalContext.current.resources))
        ),
        modifier = Modifier.size(80.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = com.mrmar.airfryer.core.R.drawable.airfryer_device_image),
            contentDescription = "Airfryer"
        )
    }
}

@Composable
fun DeviceCardContent(
    meals: List<Meal>,
    selectedMeal: Meal? = null,
    deviceStatus: DeviceStatus,
    onSelectedChanged: (Meal) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 60.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.device_name), fontWeight = FontWeight.Bold)
        Text(text = buildAnnotatedString {
            append(bullet)
            append("\t\t")
            append(deviceStatus.getStringResource(LocalContext.current.resources))
            addStyle(
                SpanStyle(
                    Color(deviceStatus.getColorResource(LocalContext.current.resources)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ), 0, 1
            )
        }, style = MaterialTheme.typography.body2)
        BasicFunctionsChips(meals, selectedMeal, deviceStatus == DeviceStatus.ONLINE, onSelectedChanged)
    }
}

@Composable
fun BasicFunctionsChips(
    meals: List<Meal>,
    selectedMeal: Meal? = null,
    isPanelEnabled: Boolean,
    onSelectedChanged: (Meal) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp)
        ) {
            items(meals) { meal ->
                ImageChip(
                    text = meal.name,
                    icon = painterResource(meal.imageResource),
                    isEnabled = isPanelEnabled,
                    isSelected = meal == selectedMeal,
                    onClick = { onSelectedChanged(meal) }
                )
            }
        }
    }
}

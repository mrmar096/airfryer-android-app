package com.mrmar.airfryer.core.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrmar.airfryer.core.presentation.router.Router
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewEvent
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewSideEffect
import com.mrmar.airfryer.core.presentation.viewmodel.components.ViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect>(
    val stateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var initialData: Any

    @Inject
    lateinit var router: Router

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)

    val viewState: State<UiState> = _viewState

    val state get() = _viewState.value

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()

    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    protected abstract fun setInitialState(): UiState

    protected abstract fun handleEvents(event: Event)

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}

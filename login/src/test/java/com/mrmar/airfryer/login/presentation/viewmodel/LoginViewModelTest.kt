package com.mrmar.airfryer.login.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryException
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.login.helper.CoroutineTest
import com.mrmar.airfryer.login.presentation.viewmodel.contract.LoginContract
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

@ExperimentalCoroutinesApi
class LoginViewModelTest : CoroutineTest() {

    @Mock
    lateinit var stateHandle: SavedStateHandle

    @Mock
    lateinit var loginRepository: LoginRepository

    @Mock
    lateinit var resources: Resources

    @InjectMocks
    lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        whenever(resources.getString(any())).thenReturn("string resource")
    }


    @Test
    fun setInitialState_givenErrorLogin_shouldSetStateWithError() {
        runBlockingTest {
            whenever(stateHandle.get<String>("error")).thenReturn("error text")

            viewModel = LoginViewModel(stateHandle, loginRepository, resources)

            val expectedState = LoginContract.State(error = "error text")
            val state = viewModel.state

            state shouldBe expectedState
        }
    }

    @Test
    fun setInitialState_givenNoErrorLogin_shouldSetStateWithNoError() {
        runBlockingTest {
            whenever(stateHandle.get<String>("error")).thenReturn(null)

            viewModel = LoginViewModel(stateHandle, loginRepository, resources)

            val expectedState = LoginContract.State(error = null)
            val state = viewModel.state

            state shouldBe expectedState
        }
    }

    @Test
    fun handleEvents_givenEventUserLoginWithNoEmail_shouldShowErrorEmail() {
        runBlockingTest {
            viewModel.setEvent(LoginContract.Event.UserLogin)

            dispatcher.scheduler.advanceUntilIdle()

            val expectedState = LoginContract.State(
                errorEmail = "string resource",
            )

            val state = viewModel.state

            state shouldBe expectedState
        }
    }


    @Test
    fun handleEvents_givenEventUserChangeEmail_shouldSetStateWithEmail() {
        runBlockingTest {
            viewModel.setEvent(LoginContract.Event.EmailChanged("mario.pcdl@gmail.com"))

            dispatcher.scheduler.advanceUntilIdle()

            val expectedState = LoginContract.State(
                email = "mario.pcdl@gmail.com"
            )

            val state = viewModel.state

            state shouldBe expectedState
        }
    }

    @Test
    fun handleEvents_givenEventUserChangePassword_shouldSetStateWithPassword() {
        runBlockingTest {
            viewModel.setEvent(LoginContract.Event.PasswordChanged("123456"))

            dispatcher.scheduler.advanceUntilIdle()

            val expectedState = LoginContract.State(
                password = "123456"
            )

            val state = viewModel.state

            state shouldBe expectedState
        }
    }


    @Test
    fun handleEvents_givenEventUserLoginWithValidCredentials_whenLoginRepoKo_shouldUpdateStateWithError() {
        runBlockingTest {
            whenever(loginRepository.doLogin("mario.pcdl@gmail.com", "123456")).thenThrow(
                RepositoryException(0, "test error")
            )

            viewModel.setEvent(LoginContract.Event.EmailChanged("mario.pcdl@gmail.com"))
            viewModel.setEvent(LoginContract.Event.PasswordChanged("123456"))
            viewModel.setEvent(LoginContract.Event.UserLogin)

            dispatcher.scheduler.advanceUntilIdle()

            val expectedState = LoginContract.State(
                email = "mario.pcdl@gmail.com",
                password = "123456",
                error = "string resource"
            )

            val state = viewModel.state

            state shouldBe expectedState
        }
    }

}
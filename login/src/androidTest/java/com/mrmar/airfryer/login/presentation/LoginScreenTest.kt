package com.mrmar.airfryer.login.presentation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.mrmar.airfryer.core.ui.theme.AirfryerTheme
import com.mrmar.airfryer.login.LoginActivity
import com.mrmar.airfryer.login.clearAndInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<LoginActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            AirfryerTheme {
                LoginScreen()
            }
        }
    }

    @Test
    fun testLogInRightCredentialsShouldSuccess() {
        composeRule.apply {
            onNodeWithTag("emailField").clearAndInput("mario.pcdl@gmail.com")
            onNodeWithTag("passwordField").clearAndInput("12345")
            onNodeWithTag("loginButton").performClick()

            onNodeWithTag("errorLogin").assertDoesNotExist()
        }

    }

    @Test
    fun testLogInInvalidEmailShouldShowEmailError() {
        composeRule.apply {
            onNodeWithTag("emailField").clearAndInput("mario.pcdl@l")
            onNodeWithTag("passwordField").clearAndInput("12345")
            onNodeWithTag("loginButton").performClick()

            onNodeWithTag("emailFieldError").assertTextEquals("You\'ve entered a wrong email.")
        }

    }

    @Test
    fun testLogInWrongCredentialsShouldFail() {
        composeRule.apply {
            onNodeWithTag("emailField").clearAndInput("mario@gmail.com")
            onNodeWithTag("passwordField").clearAndInput("12341125")
            onNodeWithTag("loginButton").performClick()
            onNodeWithTag("errorLogin").assertTextEquals("Wrong email or password. Please try again.")
        }
    }

}
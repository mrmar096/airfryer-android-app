package com.mrmar.airfryer

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import org.junit.Assert
import org.junit.rules.TestRule

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}

fun SemanticsNodeInteraction.clearAndInput(input: String) = apply {
    performTextClearance()
    performTextInput(input)
}


fun <R : TestRule, A : ComponentActivity> AndroidComposeTestRule<R, A>.setContentOnActivity(
    content: @Composable () -> Unit
) {
    activity.runOnUiThread {
        activity.setContent {
            content()
        }
    }
}
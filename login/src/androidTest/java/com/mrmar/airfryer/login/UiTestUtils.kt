package com.mrmar.airfryer.login

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput

const val TIMEOUT_SHOW = 5000L
fun SemanticsNodeInteraction.clearAndInput(input: String) = apply {
    performTextClearance()
    performTextInput(input)
}
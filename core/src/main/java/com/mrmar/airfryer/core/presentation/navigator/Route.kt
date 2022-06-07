package com.mrmar.airfryer.core.presentation.navigator

import androidx.navigation.NavOptions

interface Route<out Params> {
    fun getFilledUri(): String
    fun buildOptions(): NavOptions
}
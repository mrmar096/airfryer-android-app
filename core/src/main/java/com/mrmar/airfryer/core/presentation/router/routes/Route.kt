package com.mrmar.airfryer.core.presentation.router.routes

import androidx.navigation.NavOptions

interface Route {
    fun getUriData(): String
    fun buildOptions(): NavOptions
}
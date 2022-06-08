package com.mrmar.airfryer.core.presentation.router

import androidx.navigation.NavGraphBuilder

interface RouterGraph {
    fun build(graphBuilder: NavGraphBuilder)
}
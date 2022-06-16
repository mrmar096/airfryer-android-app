package com.mrmar.airfryer.domain.models

data class Meal(
    val name: String,
    val imageResource: Int,
    val cookSetup: CookSetup
)
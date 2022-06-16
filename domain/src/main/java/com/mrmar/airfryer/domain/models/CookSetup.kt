package com.mrmar.airfryer.domain.models

data class CookSetup(
    val temp: Int,
    val time: Int,
    val requireShake: Boolean = false
)
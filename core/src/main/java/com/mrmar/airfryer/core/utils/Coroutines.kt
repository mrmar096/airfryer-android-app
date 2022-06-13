package com.mrmar.airfryer.core.utils

import kotlinx.coroutines.delay

suspend fun delayedData(duration: Long = 2000L, dataProvider: suspend () -> Unit) {
    delay(duration)
    dataProvider()
}
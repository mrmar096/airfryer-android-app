package com.mrmar.airfryer.domain.repository.device

import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal

interface DeviceRepository {
    suspend fun getStatus(): DeviceStatus
    suspend fun startCooking(meal: Meal)
    suspend fun finishCooking()
}
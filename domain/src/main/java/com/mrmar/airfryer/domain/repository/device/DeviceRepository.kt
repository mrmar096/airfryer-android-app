package com.mrmar.airfryer.domain.repository.device

import com.mrmar.airfryer.domain.models.DeviceStatus

interface DeviceRepository {
    suspend fun getStatus(): DeviceStatus
}
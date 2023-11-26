package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal
import com.mrmar.airfryer.domain.repository.device.DeviceRepository

object DeviceRepositoryTestFake : DeviceRepository {
    override suspend fun getStatus(): DeviceStatus {
        TODO("Not yet implemented")
    }

    override suspend fun startCooking(meal: Meal) {
        TODO("Not yet implemented")
    }

    override suspend fun finishCooking() {
        TODO("Not yet implemented")
    }
}
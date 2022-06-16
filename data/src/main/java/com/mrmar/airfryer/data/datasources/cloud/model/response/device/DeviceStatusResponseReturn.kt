package com.mrmar.airfryer.data.datasources.cloud.model.response.device

import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook.CookStatusMixed

internal data class DeviceStatusResponseReturn(
    @SerializedName("cookStatus")
    val value: CookStatusMixed
)

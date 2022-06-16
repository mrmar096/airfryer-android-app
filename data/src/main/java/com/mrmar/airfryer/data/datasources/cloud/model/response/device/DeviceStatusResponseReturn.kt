package com.mrmar.airfryer.data.datasources.cloud.model.response.device

import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.response.cook.CookStatusResponse

internal data class DeviceStatusResponseReturn(
    @SerializedName("cookStatus")
    val value: CookStatusResponse
)

package com.mrmar.airfryer.data.datasources.cloud.model.response.device

import com.google.gson.annotations.SerializedName

internal data class DeviceStatusResponseResult(
    @SerializedName("returnStatus")
    val status: DeviceStatusResponseReturn
)

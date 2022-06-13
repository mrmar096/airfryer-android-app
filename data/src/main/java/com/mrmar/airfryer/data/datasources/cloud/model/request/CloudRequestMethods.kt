package com.mrmar.airfryer.data.datasources.cloud.model.request

import com.google.gson.annotations.SerializedName

enum class CloudRequestMethods {
    @SerializedName("getHomeDeviceStatus")
    Status,

    @SerializedName("bypass")
    Operation
}
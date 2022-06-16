package com.mrmar.airfryer.data.datasources.cloud.model.response.cook

import com.google.gson.annotations.SerializedName

enum class CookStatusResponse {
    @SerializedName("standby")
    STANDBY,
    @SerializedName("heating")
    HEATING,
    @SerializedName("cooking")
    COOKING;
}
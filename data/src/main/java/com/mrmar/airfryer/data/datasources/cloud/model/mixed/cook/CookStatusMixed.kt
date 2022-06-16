package com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook

import com.google.gson.annotations.SerializedName

enum class CookStatusMixed {
    @SerializedName("standby")
    STANDBY,
    @SerializedName("heating")
    HEATING,
    @SerializedName("cooking")
    COOKING,
    @SerializedName("cookStop")
    PAUSE_COOKING,
    @SerializedName("end")
    END_COOKING;
}
package com.mrmar.airfryer.data.datasources.cloud.model.request

import com.google.gson.annotations.SerializedName

internal data class CloudRequestDevice(
    @SerializedName("cid")
    val cid: String,
    @SerializedName("subDeviceNo")
    val subDeviceNo: Int = 0
)
package com.mrmar.airfryer.data.cloud.model.response

import com.google.gson.annotations.SerializedName

internal open class CloudResponseModel {
    @SerializedName("code")
    val code: Int? = null

    @SerializedName("msg")
    val msg: String? = null

    @SerializedName("traceId")
    val traceId: String? = null
}

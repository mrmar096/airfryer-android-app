package com.mrmar.airfryer.data.datasources.cloud.model.response.login

import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.response.CloudResponseModel

internal data class LoginResponseModel(
    @SerializedName("accountID")
    val accountID: String? = null,
    @SerializedName("token")
    val token: String? = null
): CloudResponseModel()

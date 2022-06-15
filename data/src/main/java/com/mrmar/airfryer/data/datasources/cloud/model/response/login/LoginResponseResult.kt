package com.mrmar.airfryer.data.datasources.cloud.model.response.login

import com.google.gson.annotations.SerializedName

internal data class LoginResponseResult(
    @SerializedName("accountID")
    val accountID: String,
    @SerializedName("token")
    val token: String
)

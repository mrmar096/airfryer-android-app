package com.mrmar.airfryer.data.datasources.cloud.model.request.login

import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestMethods
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModel

private const val LOGIN_TRACE_ID = "1655280299437"

internal data class LoginRequestModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userType")
    val userType: String = "1"
) : CloudRequestModel(method = CloudRequestMethods.Login, traceId = LOGIN_TRACE_ID)
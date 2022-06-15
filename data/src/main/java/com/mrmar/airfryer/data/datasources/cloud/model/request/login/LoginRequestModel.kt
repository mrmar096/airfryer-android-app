package com.mrmar.airfryer.data.datasources.cloud.model.request.login

import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestMethods
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModel

internal data class LoginRequestModel(
    @SerializedName("email")
    val email: String = "es",
    @SerializedName("password")
    val password: String?,
) : CloudRequestModel(method = CloudRequestMethods.Status)
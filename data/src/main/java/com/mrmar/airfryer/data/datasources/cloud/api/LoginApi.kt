package com.mrmar.airfryer.data.datasources.cloud.api

import com.mrmar.airfryer.data.datasources.cloud.model.request.login.LoginRequestModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.login.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.POST


internal interface LoginApi {
    @POST("user/loginV2")
    suspend fun login(@Body request: LoginRequestModel): LoginResponseModel
}
package com.mrmar.airfryer.data.datasources.cloud.api

import com.mrmar.airfryer.data.datasources.cloud.model.response.CloudResponseModel
import retrofit2.http.POST


internal interface LoginApi {
    @POST("homeManaged/getHomeDeviceStatus")
    suspend fun getStatus(): CloudResponseModel
}
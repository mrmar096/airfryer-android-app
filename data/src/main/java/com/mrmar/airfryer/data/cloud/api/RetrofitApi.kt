package com.mrmar.airfryer.data.cloud.api

import com.mrmar.airfryer.data.cloud.model.response.CloudResponseModel
import retrofit2.http.POST


internal interface RetrofitApi {
    @POST("homeManaged/getHomeDeviceStatus")
    suspend fun getStatus(): CloudResponseModel
}
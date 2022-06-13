package com.mrmar.airfryer.data.datasources.cloud.api

import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.CloudResponseModel
import retrofit2.http.Body
import retrofit2.http.POST


internal interface DeviceApi {
    @POST("homeManaged/getHomeDeviceStatus")
    suspend fun getStatus(@Body cloudRequestModel: CloudRequestModel): CloudResponseModel
}
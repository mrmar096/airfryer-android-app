package com.mrmar.airfryer.data.datasources.cloud.api

import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.GenericResponseResultModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.device.DeviceStatusResponseResult
import retrofit2.http.Body
import retrofit2.http.POST


internal interface DeviceApi {
    @POST("deviceManaged/bypass")
    suspend fun sendOperation(@Body cloudRequestModel: CloudRequestModel): GenericResponseResultModel<DeviceStatusResponseResult>
}
package com.mrmar.airfryer.data.datasources.cloud.model.response

import com.google.gson.annotations.SerializedName

internal data class GenericResponseResultModel<T>(
    @SerializedName("result")
    val result: T? = null
) : CloudResponseModel()

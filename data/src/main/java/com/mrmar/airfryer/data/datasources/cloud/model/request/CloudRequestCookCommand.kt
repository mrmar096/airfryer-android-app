package com.mrmar.airfryer.data.datasources.cloud.model.request


import com.google.gson.annotations.SerializedName

internal data class CloudRequestCookCommand(
    @SerializedName("cookMode")
    val cookMode: CloudRequestCookMode?
)
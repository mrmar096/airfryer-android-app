package com.mrmar.airfryer.data.datasources.cloud.model.request

import com.google.gson.annotations.SerializedName


internal sealed class CloudRequestCommand {
    internal data class CloudRequestCookCommand(
        @SerializedName("cookMode")
        val cookMode: CloudRequestCookMode?
    ) : CloudRequestCommand()

    internal data class CloudRequestStatusCommand(
        @SerializedName("getStatus")
        val status: String = "status"
    ) : CloudRequestCommand()
}
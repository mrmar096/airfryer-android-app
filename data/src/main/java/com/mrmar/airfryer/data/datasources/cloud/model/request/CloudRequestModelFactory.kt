package com.mrmar.airfryer.data.datasources.cloud.model.request

internal object CloudRequestModelFactory {
    private const val DEVICE_ID = "vsske14cdf1911eb862900163e0cf75c"
    fun buildForStatus(accountId: String, token: String): CloudRequestModel {
        return CloudRequestModel(
            accountID = accountId,
            token = token,
            method = CloudRequestMethods.Status,
            deviceList = listOf(
                CloudRequestDevice(DEVICE_ID)
            )
        )
    }
}
package com.mrmar.airfryer.data.datasources.cloud.model.request

internal object CloudRequestModelFactory {
    private const val TRACE_ID = "1654112207258"
    private const val DEVICE_ID = "vsske14cdf1911eb862900163e0cf75c"
    fun buildForStatus(accountId: String, token: String): CloudRequestModel {
        return CloudRequestModel(
            accountID = accountId,
            token = token,
            method = CloudRequestMethods.Operation,
            traceId = TRACE_ID,
            cid = DEVICE_ID,
            jsonCmd = CloudRequestCommand.CloudRequestStatusCommand(),
        )
    }
}
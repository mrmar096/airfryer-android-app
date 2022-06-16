package com.mrmar.airfryer.data.datasources.cloud.model.request

import com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook.CookStatusMixed
import com.mrmar.airfryer.domain.models.Meal

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

    fun buildForStartCooking(accountId: String, token: String, meal: Meal): CloudRequestModel {
        return CloudRequestModel(
            accountID = accountId,
            token = token,
            method = CloudRequestMethods.Operation,
            traceId = TRACE_ID,
            cid = DEVICE_ID,
            jsonCmd = CloudRequestCommand.CloudRequestCookCommand(
                CloudRequestCookMode(
                    cookSetTemp = meal.cookSetup.temp,
                    cookSetTime = meal.cookSetup.time,
                    cookStatus = CookStatusMixed.COOKING,
                )
            ),
        )
    }

    fun buildForFinishCooking(accountId: String, token: String): CloudRequestModel {
        return CloudRequestModel(
            accountID = accountId,
            token = token,
            method = CloudRequestMethods.Operation,
            traceId = TRACE_ID,
            cid = DEVICE_ID,
            jsonCmd = CloudRequestCommand.CloudRequestCookCommand(
                CloudRequestCookMode(
                    cookStatus = CookStatusMixed.END_COOKING,
                )
            ),
        )
    }
}
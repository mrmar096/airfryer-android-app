package com.mrmar.airfryer.data.repository.device

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModelFactory
import com.mrmar.airfryer.data.datasources.cloud.model.response.GenericResponseResultModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.cook.CookStatusResponse
import com.mrmar.airfryer.data.datasources.cloud.model.response.device.DeviceStatusResponseResult
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.repository.device.DeviceRepository
import com.mrmar.airfryer.domain.repository.exceptions.SessionExpiredException
import javax.inject.Inject

internal class DeviceRepositoryDefault @Inject constructor(
    private val deviceApi: DeviceApi,
    sessionContextDao: SessionContextDao
) : BaseRepository(sessionContextDao), DeviceRepository {

    override suspend fun getStatus(): DeviceStatus {
        val sessionContext = sessionContextDao.getSessionContext() ?: throw SessionExpiredException
        return safe {
            deviceApi.sendOperation(
                CloudRequestModelFactory.buildForStatus(
                    sessionContext.accountId,
                    sessionContext.token.orEmpty()
                )
            ).mapToStatus()
        }
    }

    private fun GenericResponseResultModel<DeviceStatusResponseResult>.mapToStatus(): DeviceStatus {
        return when (result?.status?.value) {
            CookStatusResponse.STANDBY -> DeviceStatus.ONLINE
            CookStatusResponse.HEATING -> DeviceStatus.HEATING
            CookStatusResponse.COOKING -> DeviceStatus.COOKING
            else -> DeviceStatus.OFFLINE
        }
    }

}
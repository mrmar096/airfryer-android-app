package com.mrmar.airfryer.data.repository.device

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook.CookStatusMixed
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModelFactory
import com.mrmar.airfryer.data.datasources.cloud.model.response.GenericResponseResultModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.device.DeviceStatusResponseResult
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal
import com.mrmar.airfryer.domain.repository.device.DeviceRepository
import com.mrmar.airfryer.domain.repository.exceptions.SessionExpiredException
import javax.inject.Inject

internal class DeviceRepositoryDefault @Inject constructor(
    private val deviceApi: DeviceApi,
    sessionContextDao: SessionContextDao
) : BaseRepository(sessionContextDao), DeviceRepository {

    override suspend fun getStatus(): DeviceStatus {
        //TODO add interceptor for obtain session creds and inject in all requests
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

    override suspend fun startCooking(meal: Meal) {
        //TODO add interceptor for obtain session creds and inject in all requests
        val sessionContext = sessionContextDao.getSessionContext() ?: throw SessionExpiredException
        safe {
            deviceApi.sendOperation(
                CloudRequestModelFactory.buildForStartCooking(
                    sessionContext.accountId,
                    sessionContext.token.orEmpty(),
                    meal
                )
            )
        }
    }

    override suspend fun finishCooking() {
        //TODO add interceptor for obtain session creds and inject in all requests
        val sessionContext = sessionContextDao.getSessionContext() ?: throw SessionExpiredException
        safe {
            deviceApi.sendOperation(
                CloudRequestModelFactory.buildForFinishCooking(
                    sessionContext.accountId,
                    sessionContext.token.orEmpty()
                )
            )
        }
    }

    private fun GenericResponseResultModel<DeviceStatusResponseResult>.mapToStatus(): DeviceStatus {
        return when (result?.status?.value) {
            CookStatusMixed.STANDBY -> DeviceStatus.ONLINE
            CookStatusMixed.HEATING -> DeviceStatus.HEATING
            CookStatusMixed.COOKING -> DeviceStatus.COOKING
            CookStatusMixed.PAUSE_COOKING -> DeviceStatus.COOKING
            else -> DeviceStatus.OFFLINE
        }
    }

}
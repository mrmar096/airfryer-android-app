package com.mrmar.airfryer.data.repository.login

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModelFactory
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.repository.exceptions.SessionExpiredException
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import javax.inject.Inject

internal class LoginRepositoryDefault @Inject constructor(
    private val deviceApi: DeviceApi,
    private val sessionContextDao: SessionContextDao,
) : BaseRepository(), LoginRepository {

    override suspend fun checkUserSession() {
        val sessionContext = sessionContextDao.getSessionContext()
        val hasValidSession = sessionContext?.let { checkSession(it) } ?: false
        if (!hasValidSession) {
            throw SessionExpiredException
        }
    }

    private suspend fun checkSession(sessionContext: SessionContextEntity): Boolean {
        sessionContext.token ?: return false
        return safe {
            deviceApi.getStatus(
                CloudRequestModelFactory.buildForStatus(
                    sessionContext.accountId,
                    sessionContext.token
                )
            ).succeeded()
        }
    }

    override suspend fun handleCodeErrors(code: Int, message: String): Throwable {
        sessionContextDao.delete()
        return super.handleCodeErrors(code, message)
    }

    override suspend fun doLogin(email: String, password: String) {
        //TODO call api to do the login
        sessionContextDao.save(
            SessionContextEntity(
                "",
                ""
            )
        )
    }
}
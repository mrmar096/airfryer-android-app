package com.mrmar.airfryer.data.repository.login

import com.mrmar.airfryer.core.utils.toMD5
import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.api.LoginApi
import com.mrmar.airfryer.data.datasources.cloud.model.request.CloudRequestModelFactory
import com.mrmar.airfryer.data.datasources.cloud.model.request.login.LoginRequestModel
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.repository.exceptions.NoSessionDetectedException
import com.mrmar.airfryer.domain.repository.exceptions.WrongCredentialsException
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import javax.inject.Inject

internal class LoginRepositoryDefault @Inject constructor(
    private val deviceApi: DeviceApi,
    private val loginApi: LoginApi,
    sessionContextDao: SessionContextDao
) : BaseRepository(sessionContextDao), LoginRepository {

    private val credentialsErrors = arrayOf(-11201022, -11202022)

    override suspend fun checkUserSession() {
        val sessionContext = sessionContextDao.getSessionContext()
        sessionContext?.let { checkSession(it) } ?: throw NoSessionDetectedException
    }

    private suspend fun checkSession(sessionContext: SessionContextEntity) {
        if (sessionContext.token.isNullOrBlank()) throw NoSessionDetectedException
        safe {
            deviceApi.sendOperation(
                CloudRequestModelFactory.buildForStatus(
                    sessionContext.accountId,
                    sessionContext.token
                )
            )
        }
    }

    override suspend fun handleCodeErrors(code: Int, message: String): Throwable {
        doLogout()
        return super.handleCodeErrors(code, message)
    }

    override suspend fun doLogin(email: String, password: String) {
        val response = safe { loginApi.login(LoginRequestModel(email, password.toMD5())) }
        if (response.code in credentialsErrors) {
            throw WrongCredentialsException
        } else if (response.result == null) {
            throw NoSessionDetectedException
        }
        sessionContextDao.save(
            SessionContextEntity(
                response.result.accountID,
                response.result.token
            )
        )
    }

    override suspend fun doLogout() {
        sessionContextDao.delete()
    }
}
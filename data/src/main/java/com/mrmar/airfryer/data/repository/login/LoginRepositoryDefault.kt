package com.mrmar.airfryer.data.repository.login

import com.mrmar.airfryer.data.datasources.cloud.api.LoginApi
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import javax.inject.Inject

internal class LoginRepositoryDefault @Inject constructor(
    private val loginApi: LoginApi,
    private val sessionContextDao: SessionContextDao,
) : BaseRepository(), LoginRepository {

    override suspend fun isLoggedIn(): Boolean {
        return sessionContextDao.getToken() != null
    }
}
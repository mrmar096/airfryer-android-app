package com.mrmar.airfryer.data.repository.login

import com.mrmar.airfryer.data.datasources.cloud.api.LoginApi
import com.mrmar.airfryer.data.repository.BaseRepository
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import javax.inject.Inject

internal class LoginRepository @Inject constructor(
    private val api: LoginApi,
) : BaseRepository(), LoginRepository {

    override suspend fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}
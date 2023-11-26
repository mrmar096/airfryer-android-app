package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.domain.repository.exceptions.NoSessionDetectedException
import com.mrmar.airfryer.domain.repository.exceptions.WrongCredentialsException
import com.mrmar.airfryer.domain.repository.login.LoginRepository

object LoginRepositoryTestFake : LoginRepository {
    override suspend fun checkUserSession() {
        throw NoSessionDetectedException
    }

    override suspend fun doLogin(email: String, password: String) {
        if (email != "mario.pcdl@gmail.com" && password != "12345") {
            throw WrongCredentialsException
        }
    }

    override suspend fun doLogout() {
        //Do nothing
    }
}
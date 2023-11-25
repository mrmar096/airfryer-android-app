package com.mrmar.airfryer.login.di.fake

import com.mrmar.airfryer.domain.repository.exceptions.WrongCredentialsException
import com.mrmar.airfryer.domain.repository.login.LoginRepository

object LoginRepositoryTestFake : LoginRepository {
    override suspend fun checkUserSession() {
        //Do nothing
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
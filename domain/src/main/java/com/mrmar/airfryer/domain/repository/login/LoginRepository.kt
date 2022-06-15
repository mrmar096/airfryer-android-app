package com.mrmar.airfryer.domain.repository.login

interface LoginRepository {
    suspend fun checkUserSession()
    suspend fun doLogin(email: String, password: String)
}
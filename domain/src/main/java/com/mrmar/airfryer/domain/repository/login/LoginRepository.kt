package com.mrmar.airfryer.domain.repository.login

interface LoginRepository {
    suspend fun checkUserSession()
    suspend fun doLogin(username: String, password: String)
}
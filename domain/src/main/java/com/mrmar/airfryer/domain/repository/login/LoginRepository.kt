package com.mrmar.airfryer.domain.repository.login

interface LoginRepository {
    suspend fun isLoggedIn(): Boolean
}
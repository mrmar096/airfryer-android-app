package com.mrmar.airfryer.domain.repository

interface IRepository {
    suspend fun get(): String
}
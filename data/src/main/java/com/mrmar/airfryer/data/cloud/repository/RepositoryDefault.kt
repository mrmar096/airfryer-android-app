package com.mrmar.airfryer.data.cloud.repository

import com.mrmar.airfryer.data.cloud.api.RetrofitApi
import com.mrmar.airfryer.domain.repository.IRepository
import javax.inject.Inject

internal class RepositoryDefault @Inject constructor(
    private val api: RetrofitApi
) : BaseRepository(), IRepository {

    override suspend fun get(): String {
        return safe {
            api.getStatus()
        }.toString()
    }
}
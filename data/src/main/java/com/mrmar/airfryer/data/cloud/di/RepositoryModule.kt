package com.mrmar.airfryer.data.cloud.di

import com.mrmar.airfryer.data.cloud.api.RetrofitApi
import com.mrmar.airfryer.data.cloud.repository.RepositoryDefault
import com.mrmar.airfryer.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(api: RetrofitApi): IRepository {
        return RepositoryDefault(api)
    }
}
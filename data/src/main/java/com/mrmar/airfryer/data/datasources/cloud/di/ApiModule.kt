package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofitLoginApi(@ApiV2 retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitDeviceApi(@ApiV1 retrofit: Retrofit): DeviceApi {
        return retrofit.create(DeviceApi::class.java)
    }
}
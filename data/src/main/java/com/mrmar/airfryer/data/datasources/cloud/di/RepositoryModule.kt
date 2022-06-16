package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.api.LoginApi
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.repository.device.DeviceRepositoryDefault
import com.mrmar.airfryer.data.repository.login.LoginRepositoryDefault
import com.mrmar.airfryer.domain.repository.device.DeviceRepository
import com.mrmar.airfryer.domain.repository.login.LoginRepository
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
    fun providesLoginRepository(
        deviceApi: DeviceApi,
        loginApi: LoginApi,
        dao: SessionContextDao
    ): LoginRepository {
        return LoginRepositoryDefault(deviceApi, loginApi, dao)
    }

    @Provides
    @Singleton
    fun providesDeviceRepository(
        deviceApi: DeviceApi,
        dao: SessionContextDao
    ): DeviceRepository {
        return DeviceRepositoryDefault(deviceApi, dao)
    }
}
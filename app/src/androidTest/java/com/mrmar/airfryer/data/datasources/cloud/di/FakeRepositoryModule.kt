package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.domain.repository.device.DeviceRepository
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module()
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
internal object FakeRepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository {
        return LoginRepositoryTestFake
    }

    @Provides
    @Singleton
    fun providesDeviceRepository(): DeviceRepository {
        return DeviceRepositoryTestFake
    }
}
package com.mrmar.airfryer.core.presentation.router.di

import com.mrmar.airfryer.core.presentation.router.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RouterModule::class]
)
object FakeRouterModule {
    @Provides
    @Singleton
    fun providesRouter(): Router = FakeRouter
}
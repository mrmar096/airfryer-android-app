package com.mrmar.airfryer.core.presentation.router.di

import com.mrmar.airfryer.core.presentation.router.AirfyerRouter
import com.mrmar.airfryer.core.presentation.router.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RouterModule {
    @Provides
    @Singleton
    fun providesRouter(): Router = AirfyerRouter()
}
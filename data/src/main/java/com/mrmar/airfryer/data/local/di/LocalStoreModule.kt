package com.mrmar.airfryer.data.local.di

import android.app.Application
import androidx.room.Room
import com.mrmar.airfryer.data.BuildConfig
import com.mrmar.airfryer.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalStoreModule {

    @Provides
    @Singleton
    fun providesDataBase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }
}
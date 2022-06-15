package com.mrmar.airfryer.data.datasources.cloud.di

import com.mrmar.airfryer.data.BuildConfig
import com.mrmar.airfryer.data.BuildConfig.BASE_URL_V1
import com.mrmar.airfryer.data.BuildConfig.BASE_URL_V2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }.run {
            OkHttpClient.Builder().addInterceptor(this).build()
        }
    }


    @Provides
    @Singleton
    @ApiV1
    fun providesRetrofitApiClientV1(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_V1)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @ApiV2
    fun providesRetrofitApiClientV2(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_V2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

}
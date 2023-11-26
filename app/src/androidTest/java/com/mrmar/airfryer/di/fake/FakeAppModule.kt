package com.mrmar.airfryer.di.fake
import android.app.Application
import android.content.res.Resources
import com.mrmar.airfryer.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object FakeAppModule {

    @Provides
    fun provideResources(app: Application): Resources = app.resources
}
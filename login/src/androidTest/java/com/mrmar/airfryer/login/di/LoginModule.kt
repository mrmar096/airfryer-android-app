package com.mrmar.airfryer.login.di
import android.app.Application
import android.content.res.Resources
import com.mrmar.airfryer.domain.repository.login.LoginRepository
import com.mrmar.airfryer.login.di.fake.LoginRepositoryTestFake
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideResources(app: Application): Resources = app.resources

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository {
        return LoginRepositoryTestFake
    }
}
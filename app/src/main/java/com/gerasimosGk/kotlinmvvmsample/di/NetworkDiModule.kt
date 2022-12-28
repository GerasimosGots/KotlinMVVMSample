package com.gerasimosGk.kotlinmvvmsample.di

import com.gerasimosGk.kotlinmvvmsample.data.network.ApiClient
import com.gerasimosGk.kotlinmvvmsample.data.network.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDiModule {

    // -- Dispatcher --
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideApiClient(): Retrofit {
        return ApiClient.createPrimaryRetrofitClient()
    }

    // Services

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}
package com.gerasimosGk.kotlinmvvmsample.di

import com.gerasimosGk.kotlinmvvmsample.data.network.service.UserService
import com.gerasimosGk.kotlinmvvmsample.data.repositoty.UserRepository
import com.gerasimosGk.kotlinmvvmsample.data.repositoty.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryDiModule {

    @Singleton
    @Provides
    fun provideUserRepository(dispatcher: CoroutineDispatcher, userService: UserService): UserRepository {
        return UserRepositoryImpl(
            dispatcher = dispatcher,
            userService = userService
        )
    }
}
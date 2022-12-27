package com.gerasimosGk.kotlinmvvmsample.di

import com.gerasimosGk.kotlinmvvmsample.data.repositoty.UserRepository
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCaseImpl(
           userRepository = userRepository
        )
    }
}
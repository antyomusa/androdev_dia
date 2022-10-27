package com.anthonyyoab.androdev.di

import com.anthonyyoab.androdev.data.remote.AuthenticationRemoteDataSource
import com.anthonyyoab.androdev.data.repository.AuthenticationRepository
import com.anthonyyoab.androdev.data.service.AuthenticationService
import com.anthonyyoab.androdev.retrofit.AuthenticationRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService
            = AuthenticationRetrofit.authenticationService

    @Provides
    @Singleton
    fun provideAuthenticationRemoteDataSource(authenticationService: AuthenticationService): AuthenticationRemoteDataSource
            = AuthenticationRemoteDataSource(authenticationService)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(authenticationRemoteDataSource: AuthenticationRemoteDataSource): AuthenticationRepository
            = AuthenticationRepository(authenticationRemoteDataSource)
}
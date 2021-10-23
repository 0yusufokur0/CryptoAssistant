package com.resurrection.cryptoassistant.di

import com.resurrection.cryptoassistant.data.repository.CryptoRepositoryImpl
import com.resurrection.cryptoassistant.data.repository.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(repository: CryptoRepositoryImpl): TestRepository
}
package com.resurrection.cryptoassistant.di

import com.google.gson.GsonBuilder
import com.resurrection.cryptoassistant.data.remote.CryptoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CryptoApiModules {

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit =
         Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun createApi(retrofit: Retrofit):CryptoApiService =
        retrofit.create(CryptoApiService::class.java)


}

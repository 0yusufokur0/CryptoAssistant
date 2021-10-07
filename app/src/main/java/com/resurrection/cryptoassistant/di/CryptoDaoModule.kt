package com.resurrection.cryptoassistant.di

import android.content.Context
import androidx.room.Room
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.db.dao.CryptoDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CryptoDaoModule {

    @Provides
    @Singleton
    fun cryptoDatabase(@ApplicationContext context: Context): CryptoDatabase =
        Room.databaseBuilder(context, CryptoDatabase::class.java, "crypto").build()


    @Provides
    @Singleton
    fun coinDao(cryptoDatabase: CryptoDatabase): CryptoDao =
        cryptoDatabase.cryptoDao()
}


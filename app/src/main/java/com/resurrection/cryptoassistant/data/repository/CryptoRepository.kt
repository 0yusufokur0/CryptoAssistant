package com.resurrection.cryptoassistant.data.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.db.dao.CryptoDao
import com.resurrection.cryptoassistant.data.remote.CryptoApiService
import dagger.hilt.android.internal.Contexts.getApplication
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/*
class CryptoRepository @Inject constructor( private val cryptoDao :CryptoDao,private val cryptoApiService: CryptoApiService) {
*/
/*    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val api = retrofit.create(CryptoApiService::class.java)*//*

    val api = cryptoApiService
*/
/*    val dao = CryptoDatabase(getApplication(context)).cryptoDao()*//*

    val dao = cryptoDao
}*/

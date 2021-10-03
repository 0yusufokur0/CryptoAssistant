package com.resurrection.cryptoassistant.data

import android.app.Application
import com.google.gson.GsonBuilder
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.db.dao.CryptoDao
import com.resurrection.cryptoassistant.data.remote.CryptoApiService
import dagger.hilt.android.internal.Contexts.getApplication
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryTest(
    var anyObservable: Observable<*>, function: (Any?) -> Unit
) {
    lateinit var data: Any

    companion object {
        val api: CryptoApiService
            get() {
                val BASE_URL = "https://api.coingecko.com/api/v3/"
                val gson = GsonBuilder().setLenient().create()
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                return retrofit.create(CryptoApiService::class.java)
            }


    }



}
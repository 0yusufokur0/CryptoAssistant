package com.resurrection.cryptoassistant.data.remote


import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApiService {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getData(): Response<List<CryptoMarketModel>>



}
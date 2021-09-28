package com.resurrection.cryptoassistant.data.remote


import com.resurrection.cryptoassistant.data.model.CoinDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApiService {

    @GET("coins/markets?vs_currency=usd")
     fun getAllCrypto(): Observable<List<CryptoMarketModel>>

    @GET("coins/{id}")
    fun getCryptoByID(@Path("id") id: String): Observable<CoinDetailItem>
}
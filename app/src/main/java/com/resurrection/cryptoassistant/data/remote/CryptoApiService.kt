package com.resurrection.cryptoassistant.data.remote


import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApiService {

    @GET("coins/markets?vs_currency=usd")
    fun getAllCrypto(): Observable<List<CryptoMarketModel>>

    @GET("coins/{id}")
    fun getCryptoById(@Path("id") id: String): Observable<CryptoDetailItem>


/*    @GET("coins/{id}")
    fun getCryptoDetailById(@Path("id") id: String): Observable<CryptoDetailItem>*/
}
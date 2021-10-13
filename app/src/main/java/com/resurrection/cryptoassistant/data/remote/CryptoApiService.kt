package com.resurrection.cryptoassistant.data.remote


import com.resurrection.cryptoassistant.data.model.CryptoChartModel
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApiService {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getAllCrypto(): List<CryptoMarketModel>

    @GET("coins/{id}")
    suspend fun getCryptoById(@Path("id") id: String): CryptoDetailItem

    @GET("coins/{id}/market_chart?vs_currency=usd&days=30")
    suspend fun getCryptoChartByID(@Path("id") id: String):CryptoChartModel



    /*https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=30
*/

/*    @GET("coins/{id}")
    fun getCryptoDetailById(@Path("id") id: String): Observable<CryptoDetailItem>*/
}
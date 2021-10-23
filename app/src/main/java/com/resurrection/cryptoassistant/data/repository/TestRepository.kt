package com.resurrection.cryptoassistant.data.repository

import com.resurrection.cryptoassistant.data.model.CryptoChartModel
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.util.Resource
import kotlinx.coroutines.flow.Flow

interface TestRepository {

    // Network
    suspend fun getAllCrypto(): Flow<Resource<List<CryptoMarketModel>>>
    suspend fun getCryptoDetailById(id: String): Flow<Resource<CryptoDetailItem>>
    suspend fun getCryptoChartByID(id: String): Flow<Resource<CryptoChartModel>>

    // Database
    suspend fun insertFavoriteCrypto(cmm: CryptoMarketModel):Flow<Resource<Unit>>
    suspend fun getCryptoFavorite(): Flow<Resource<List<CryptoMarketModel>>>
    suspend fun getCryptoById(cryptoId:String):Flow<Resource<CryptoMarketModel>>



}

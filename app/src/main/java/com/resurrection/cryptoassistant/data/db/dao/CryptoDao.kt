package com.resurrection.cryptoassistant.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrypto(listCrypto: List<CryptoMarketModel>)

    @Query("SELECT * FROM crypto_market_model WHERE name LIKE '%' || :parameter || '%' OR symbol LIKE '%' || :parameter || '%'")
    suspend fun getCryptoByParameter(parameter: String): List<CryptoMarketModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCrypto(cryptoDetailItem:CryptoDetailItem)

    @Query("SELECT * FROM crypto_favorite")
    suspend fun getCryptoFavoriteByParameter(): List<CryptoDetailItem>
}
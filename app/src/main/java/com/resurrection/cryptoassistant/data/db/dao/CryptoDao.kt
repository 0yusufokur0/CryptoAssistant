package com.resurrection.cryptoassistant.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.resurrection.cryptoassistant.data.db.entity.CoinMarketEntity
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel

@Dao
interface CryptoDao {

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrypto(listCrypto: List<CoinMarketEntity>)*/

 /*   @Query("SELECT * FROM table_coin WHERE name LIKE '%' || :parameter || '%' OR symbol LIKE '%' || :parameter || '%'")
    suspend fun getCryptoByParameter(parameter: String): CoinMarketEntity*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCrypto(cmm: CryptoMarketModel)

    @Query("SELECT * FROM crypto_market_model")
    suspend fun getCryptoFavorite(): List<CryptoMarketModel>


    @Query("SELECT * FROM crypto_market_model where cryptoId = :cryptoId")
    suspend fun getCryptoById(cryptoId:String):CryptoMarketModel




}
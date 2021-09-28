package com.resurrection.cryptoassistant.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.resurrection.cryptoassistant.data.db.entity.CryptoMarketEntity

interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrypto(listCrypto: List<CryptoMarketEntity>)

    @Query("SELECT * FROM table_crypto WHERE name LIKE '%' || :parameter || '%' OR symbol LIKE '%' || :parameter || '%'")
    suspend fun getCryptoByParameter(parameter: String): List<CryptoMarketEntity>
}
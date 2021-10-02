package com.resurrection.cryptoassistant.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_coin")
data class CoinMarketEntity(
    @PrimaryKey
    val cryptoID: String,

    val currentPrice: Double,

    val highestPrice24h: Double,

    val cryptoImage: String,

    val lastUpdated: String,

    val lowestPrice24h: Double,

    val name: String,

    val priceChange24h: Double,

    val priceChangePercentage24h: Double,

    val symbol: String
)
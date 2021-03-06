package com.resurrection.cryptoassistant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "crypto_market_model")
data class CryptoMarketModel(
    @PrimaryKey
    @SerializedName("id")
    val cryptoId: String,

    @SerializedName("current_price")
    val currentPrice: Double,

    @SerializedName("high_24h")
    val highestPrice24h: Double,

    @SerializedName("image")
    val cryptoImage: String,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("low_24h")
    val lowestPrice24h: Double,

    @SerializedName("name")
    val name: String,

    @SerializedName("price_change_24h")
    val priceChange24h: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,

    @SerializedName("symbol")
    val symbol: String,

    )
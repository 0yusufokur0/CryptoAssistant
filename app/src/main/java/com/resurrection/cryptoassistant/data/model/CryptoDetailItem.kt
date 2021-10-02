package com.resurrection.cryptoassistant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "crypto_detail")
data class CryptoDetailItem(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("description")
    val description: Description,

    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?,

    @SerializedName("image")
    val image: Image,

    @SerializedName("market_data")
    val marketData: MarketData,

    @SerializedName("last_updated")
    val lastUpdated: String,
)
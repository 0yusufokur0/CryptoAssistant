package com.resurrection.cryptoassistant.data.model

import com.google.gson.annotations.SerializedName
import com.resurrection.cryptoassistant.data.model.CurrentPrice
import com.resurrection.cryptoassistant.data.model.High24h
import com.resurrection.cryptoassistant.data.model.Low24h

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("price_change_24h")
    val priceChange24h: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,

    @SerializedName("high_24h")
    val highestPrice24h: High24h,

    @SerializedName("low_24h")
    val lowestPrice24h: Low24h,

    )
package com.resurrection.cryptoassistant.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CryptoChartModel(
    @SerializedName("prices") val prices : List<List<Double>>,
    @SerializedName("market_caps") val market_caps : List<List<Double>>,
    @SerializedName("total_volumes") val total_volumes : List<List<Double>>

)
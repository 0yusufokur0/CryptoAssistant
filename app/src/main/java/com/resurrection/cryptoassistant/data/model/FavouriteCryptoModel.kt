package com.resurrection.cryptoassistant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_crypto_model")
data class FavouriteCryptoModel(
    @PrimaryKey
    val id: String,
    val currentPrice: String,
    val saveTime:String

) {
    constructor() : this("","","")
}
package com.resurrection.cryptoassistant.data.model

data class FavouriteCryptoModel(
    val id: String,
    val image: String,
    val name: String,
    val symbol: String
) {
    constructor() : this("", "", "", "")
}
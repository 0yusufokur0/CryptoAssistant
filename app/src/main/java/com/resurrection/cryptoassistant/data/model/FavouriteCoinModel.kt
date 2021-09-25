package com.aokur.bitcointicker.data.model

data class FavouriteCoinModel(
    val id: String,
    val image: String,
    val name: String,
    val symbol: String
) {
    constructor() : this("", "", "", "")
}
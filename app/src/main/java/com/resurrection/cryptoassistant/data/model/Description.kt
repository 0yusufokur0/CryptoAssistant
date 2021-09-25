package com.aokur.bitcointicker.data.model

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String,
)
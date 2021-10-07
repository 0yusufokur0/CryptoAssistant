package com.resurrection.cryptoassistant.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.resurrection.cryptoassistant.data.model.Description
import com.resurrection.cryptoassistant.data.model.Image
import com.resurrection.cryptoassistant.data.model.MarketData

class TypeConverter {

    @TypeConverter
    fun fromDescriptionLangGson(value: Description): String {
        val gson = Gson()
        val type = object : TypeToken<Description>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDescriptionLangList(value: String): Description {
        val gson = Gson()
        val type = object : TypeToken<Description>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImageLangGson(value: Image): String {
        val gson = Gson()
        val type = object : TypeToken<Image>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImageLangList(value: String): Image {
        val gson = Gson()
        val type = object : TypeToken<Image>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun fromMarketDataangGson(value: MarketData): String {
        val gson = Gson()
        val type = object : TypeToken<MarketData>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMarketDataLangList(value: String): MarketData {
        val gson = Gson()
        val type = object : TypeToken<MarketData>() {}.type
        return gson.fromJson(value, type)
    }
}
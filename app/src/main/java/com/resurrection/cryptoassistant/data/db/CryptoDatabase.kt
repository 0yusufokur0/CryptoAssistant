package com.resurrection.cryptoassistant.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.resurrection.cryptoassistant.data.db.dao.CryptoDao
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel

@Database(
    entities = [CryptoMarketModel::class, CryptoDetailItem::class, FavouriteCryptoModel::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}

package com.resurrection.cryptoassistant.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.resurrection.cryptoassistant.DataConverter
import com.resurrection.cryptoassistant.data.db.dao.CryptoDao
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel

@Database(entities = arrayOf(CryptoMarketModel::class,CryptoDetailItem::class,FavouriteCryptoModel::class),version = 1)
@TypeConverters(DataConverter::class)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao() : CryptoDao

    companion object {
        @Volatile private var instance : CryptoDatabase? = null
        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CryptoDatabase::class.java,"crypto"
        ).build()
    }
}

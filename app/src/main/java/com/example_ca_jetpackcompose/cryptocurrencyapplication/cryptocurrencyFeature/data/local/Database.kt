package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract val dao: Dao
}
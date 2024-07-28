package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM Coin")
    fun getCoinsFromDatabase() : Flow<List<Coin>>
    @Query("SELECT * FROM Coin WHERE id=:coinId")
    suspend fun getCoinByIdFromDatabase(coinId: String) : Coin?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCoin(coin: Coin)
    @Delete
    suspend fun deleteCoin(coin: Coin)
}
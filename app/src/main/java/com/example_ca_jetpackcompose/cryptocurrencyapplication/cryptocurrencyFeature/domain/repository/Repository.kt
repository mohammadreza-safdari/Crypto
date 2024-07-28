package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.CoinDetail
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCoins() : Flow<Resource<List<Coin>>>
    suspend fun getCoinDetailsById(coinId: String) : CoinDetail
    fun getCoinsFromDatabase() : Flow<List<Coin>>
    suspend fun getCoinByIdFromDatabase(coinId: String) : Coin?
    suspend fun upsertCoin(coin: Coin)
    suspend fun deleteCoin(coin: Coin)
}
package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository

class GetCoinByIdFromDatabase constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(coinId: String) : Coin? {
        return repository.getCoinByIdFromDatabase(coinId)
    }
}
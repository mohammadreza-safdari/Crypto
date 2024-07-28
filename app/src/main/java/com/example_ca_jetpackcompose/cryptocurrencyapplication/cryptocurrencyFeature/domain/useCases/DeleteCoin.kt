package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository

class DeleteCoin constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(coin: Coin) {
        repository.deleteCoin(coin)
    }
}
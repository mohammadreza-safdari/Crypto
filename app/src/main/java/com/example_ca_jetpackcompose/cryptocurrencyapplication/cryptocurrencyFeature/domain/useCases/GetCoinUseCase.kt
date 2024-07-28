package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.CoinDetail
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository

class GetCoinUseCase constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(coinId: String) : CoinDetail {
        return repository.getCoinDetailsById(coinId)
    }
}
package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCoinsUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke() : Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}
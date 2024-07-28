package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetCoinsFromDatabaseUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke() : Flow<List<Coin>> {
        return repository.getCoinsFromDatabase()
    }
}
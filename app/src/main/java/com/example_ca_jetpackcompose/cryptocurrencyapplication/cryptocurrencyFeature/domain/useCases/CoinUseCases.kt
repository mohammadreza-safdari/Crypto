package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases

data class CoinUseCases(
    val getCoinUseCase: GetCoinUseCase,
    val getCoinsUseCase: GetCoinsUseCase,
    val getCoinsFromDatabaseUseCase: GetCoinsFromDatabaseUseCase,
    val getCoinByIdFromDatabase: GetCoinByIdFromDatabase,
    val upsertCoin: UpsertCoin,
    val deleteCoin: DeleteCoin
)
package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinListScreen

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin

data class CoinListState(
    val coins: List<Coin> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
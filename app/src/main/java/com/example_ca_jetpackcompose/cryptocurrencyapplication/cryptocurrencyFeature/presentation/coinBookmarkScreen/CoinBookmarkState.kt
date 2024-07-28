package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinBookmarkScreen

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin

data class CoinBookmarkState(
    val coins: List<Coin> = emptyList()
)
package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin

sealed class CoinDetailsEvent {
    data class UpsertDeleteArticleEvent(val coin: Coin) : CoinDetailsEvent()
}
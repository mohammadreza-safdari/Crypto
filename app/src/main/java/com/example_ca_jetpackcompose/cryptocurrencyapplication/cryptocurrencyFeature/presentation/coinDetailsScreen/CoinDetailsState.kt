package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.CoinDetail

data class CoinDetailsState(
    val marked: Boolean = false,
    val coinDetail: CoinDetail? = null
)
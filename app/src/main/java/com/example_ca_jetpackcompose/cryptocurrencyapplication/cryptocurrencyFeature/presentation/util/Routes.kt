package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util

sealed class Routes(val route: String) {
    object CoinListScreen: Routes("coinListScreen")
    object CoinDetailsScreen: Routes("coinDetailsScreen")
    object CoinBookmarkScreen: Routes("coinBookmarkScreen")
}
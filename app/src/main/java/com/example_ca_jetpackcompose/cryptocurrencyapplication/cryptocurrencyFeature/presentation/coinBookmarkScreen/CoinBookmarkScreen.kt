package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinBookmarkScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.CoinUseCases
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.CoinDetailsViewModel
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinListScreen.CoinListViewModel
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinListScreen.components.CoinListItem
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Dimensions

@Composable
fun CoinBookmarkScreen(
    navigateToCoinDetailsScreen: (Coin) -> Unit
) {
    val viewModel: CoinBookmarkViewModel = hiltViewModel()
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = Dimensions.SmallPadding)) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navigateToCoinDetailsScreen(coin)
                    }
                )
            }
        }
    }
}
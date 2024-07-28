package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinBookmarkScreen.CoinBookmarkScreen
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.CoinDetailsScreen
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinListScreen.CoinListScreen
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Dimensions
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Routes


@Composable
fun NavigationGraph(navController: NavHostController, startDestination: String, bottomPadding: Dp) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(bottom = bottomPadding)
    ) {
        composable(route = Routes.CoinListScreen.route) {
            CoinListScreen(navigateToDetailsScreen = { coin ->
                navigateToCoinDetailsScreen(navController, coin)
            })
        }
        composable(route = Routes.CoinDetailsScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<Coin?>("coin")
                ?.let { coin ->
                    CoinDetailsScreen(coin = coin, navigateUp = {
                        navController.navigateUp()
                    })
                }
        }
        composable(route = Routes.CoinBookmarkScreen.route) {
            CoinBookmarkScreen(navigateToCoinDetailsScreen = { coin ->
                navigateToCoinDetailsScreen(navController, coin)
            })
        }
    }
}
fun navigateToCoinDetailsScreen(navController: NavHostController, coin: Coin) {
    navController.currentBackStackEntry?.savedStateHandle?.set("coin", coin)
    navController.navigate(
        route = Routes.CoinDetailsScreen.route
    )
}
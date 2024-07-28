package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.mainScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example_ca_jetpackcompose.cryptocurrencyapplication.R
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.mainScreen.components.BottomNavigationItem
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.mainScreen.components.NewsBottomNavigation
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.navigation.NavigationGraph
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Routes
import com.example_ca_jetpackcompose.cryptocurrencyapplication.ui.theme.CryptocurrencyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CryptocurrencyApplicationTheme(content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isSystemInDarkMode = isSystemInDarkTheme()
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = Color.Transparent,
                            darkIcons = false,
                            isNavigationBarContrastEnforced = false
                        )
                    }
                    Greeting()
                }
            })
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Greeting() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    selectedItem = remember(backstackState?.destination?.route) {
        when (backstackState?.destination?.route) {
            Routes.CoinListScreen.route -> 0
            Routes.CoinBookmarkScreen.route -> 1
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Routes.CoinListScreen.route ||
                backstackState?.destination?.route == Routes.CoinBookmarkScreen.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTap(
                                navController = navController,
                                route = Routes.CoinListScreen.route
                            )

                            1 -> navigateToTap(
                                navController = navController,
                                route = Routes.CoinBookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavigationGraph(
            navController = navController,
            startDestination = Routes.CoinListScreen.route,
            bottomPadding = bottomPadding
        )
    }
}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { coinsScreen ->
            popUpTo(coinsScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}



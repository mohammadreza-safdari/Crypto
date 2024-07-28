package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinBookmarkScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.CoinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinBookmarkViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases
) : ViewModel() {
    private val _state = mutableStateOf(CoinBookmarkState())
    val state: State<CoinBookmarkState> = _state
    init {
        getCoinsFromDatabase()
    }
    private fun getCoinsFromDatabase() {
        viewModelScope.launch {
            coinUseCases.getCoinsFromDatabaseUseCase().collect { coins ->
                _state.value = _state.value.copy(coins = coins)
            }
        }
    }
}
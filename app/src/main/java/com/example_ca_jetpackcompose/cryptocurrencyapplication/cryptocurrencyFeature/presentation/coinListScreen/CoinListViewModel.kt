package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.CoinUseCases
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state
    init {
        getCoins()
    }
    private fun getCoins() {
        coinUseCases.getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(coins = result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(errorMessage = result.message)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = result.isLoading)
                }
            }
        }.launchIn(viewModelScope)
    }
}
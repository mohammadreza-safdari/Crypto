package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.CoinDetail
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.CoinUseCases
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state
    fun getCoinDetails(coinId: String) {
        viewModelScope.launch {
            val coinDetail: CoinDetail = coinUseCases.getCoinUseCase(coinId)
            _state.value = _state.value.copy(coinDetail = coinDetail)
        }
    }
    fun onEvent(event: CoinDetailsEvent) {
        when(event) {
            is CoinDetailsEvent.UpsertDeleteArticleEvent -> {
                viewModelScope.launch {
                    val coinExist = coinUseCases.getCoinByIdFromDatabase(event.coin.id)
                    if (coinExist == null) {
                        coinUseCases.upsertCoin(event.coin)
                        _state.value = _state.value.copy(marked = true)
                    } else {
                        coinUseCases.deleteCoin(event.coin)
                        _state.value = _state.value.copy(marked = false)
                    }
                }
            }
        }
    }
    fun checkMarkCondition(case: Coin) {
        viewModelScope.launch {
            val article = coinUseCases.getCoinByIdFromDatabase(case.id)
            if (article == null) {
                _state.value = _state.value.copy(marked = false)
            } else {
                _state.value = _state.value.copy(marked = true)
            }
        }
    }
}
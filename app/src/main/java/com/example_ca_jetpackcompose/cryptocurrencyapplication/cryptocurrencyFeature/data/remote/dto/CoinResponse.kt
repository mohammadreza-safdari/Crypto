package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
fun CoinResponse.toCoin() : Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,
    )
}
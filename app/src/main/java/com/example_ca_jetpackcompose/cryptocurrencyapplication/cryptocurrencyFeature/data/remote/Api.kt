package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.CoinDetailResponse
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinResponse>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String) : CoinDetailResponse
}
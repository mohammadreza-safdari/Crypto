package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model

import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
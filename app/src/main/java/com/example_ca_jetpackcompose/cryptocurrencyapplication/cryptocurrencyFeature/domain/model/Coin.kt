package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Coin(
    @PrimaryKey
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
) : Parcelable

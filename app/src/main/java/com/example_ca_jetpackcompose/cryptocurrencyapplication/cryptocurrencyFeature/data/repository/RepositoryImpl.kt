package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.local.Dao
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.Api
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.CoinDetailResponse
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.CoinResponse
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.toCoin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.dto.toCoinDetail
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.CoinDetail
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepositoryImpl constructor(
    private val api: Api,
    private val dao: Dao
): Repository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getCoins(): Flow<Resource<List<Coin>>> {
        return flow {
            emit(Resource.Loading<List<Coin>>(isLoading = true))
            val coins = try {
                api.getCoins().map { coinResponse ->
                    coinResponse.toCoin()
                }
            } catch (error: HttpException) {
                emit(Resource.Error<List<Coin>>(message = error.message))
                emit(Resource.Loading<List<Coin>>(isLoading = false))
                null
            } catch (error: IOException) {
                emit(Resource.Error<List<Coin>>(message = error.message))
                emit(Resource.Loading<List<Coin>>(isLoading = false))
                null
            }
            coins?.let {
                emit(Resource.Success<List<Coin>>(data = coins))
                emit(Resource.Loading<List<Coin>>(isLoading = false))
            }
        }
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetail {
        return api.getCoinById(coinId).toCoinDetail()
    }


    override fun getCoinsFromDatabase(): Flow<List<Coin>> {
        return dao.getCoinsFromDatabase()
    }

    override suspend fun getCoinByIdFromDatabase(coinId: String): Coin? {
        return dao.getCoinByIdFromDatabase(coinId)
    }

    override suspend fun upsertCoin(coin: Coin) {
        dao.upsertCoin(coin)
    }

    override suspend fun deleteCoin(coin: Coin) {
        dao.deleteCoin(coin)
    }

}
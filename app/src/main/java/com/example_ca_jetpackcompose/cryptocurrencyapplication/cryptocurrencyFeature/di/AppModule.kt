package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.di

import android.app.Application
import androidx.room.Room
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.local.Dao
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.local.Database
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.remote.Api
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.data.repository.RepositoryImpl
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.repository.Repository
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.CoinUseCases
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.DeleteCoin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.GetCoinByIdFromDatabase
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.GetCoinUseCase
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.GetCoinsFromDatabaseUseCase
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.GetCoinsUseCase
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.useCases.UpsertCoin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi() : Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
    @Provides
    @Singleton
    fun provideDatabase(application: Application) : Database {
        return Room.databaseBuilder(
            context = application,
            klass = Database::class.java,
            name = "coin_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: Database) : Dao = database.dao
    @Provides
    @Singleton
    fun provideCryptocurrencyRepository(
        api: Api,
        dao: Dao
    ) : Repository = RepositoryImpl(api = api, dao = dao)
    @Provides
    @Singleton
    fun provideCoinUseCases(repository: Repository) : CoinUseCases {
        return CoinUseCases(
            getCoinUseCase = GetCoinUseCase(repository),
            getCoinsUseCase = GetCoinsUseCase(repository),
            getCoinsFromDatabaseUseCase = GetCoinsFromDatabaseUseCase(repository),
            getCoinByIdFromDatabase = GetCoinByIdFromDatabase(repository),
            upsertCoin = UpsertCoin(repository),
            deleteCoin = DeleteCoin(repository)
        )
    }
}
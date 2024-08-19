package com.hfad.cryptoapp.di

import android.app.Application
import com.hfad.cryptoapp.data.database.AppDatabase
import com.hfad.cryptoapp.data.database.CoinInfoDao
import com.hfad.cryptoapp.data.network.ApiFactory
import com.hfad.cryptoapp.data.network.ApiService
import com.hfad.cryptoapp.data.repository.CoinRepositoryImpl
import com.hfad.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}
package com.hfad.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.hfad.cryptoapp.data.database.AppDatabase
import com.hfad.cryptoapp.data.database.CoinInfoDao
import com.hfad.cryptoapp.data.mapper.CoinMapper
import com.hfad.cryptoapp.data.network.ApiFactory
import com.hfad.cryptoapp.data.workers.RefreshDataWorker
import com.hfad.cryptoapp.domain.CoinInfo
import com.hfad.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val application: Application
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map { dbList ->
            dbList.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}
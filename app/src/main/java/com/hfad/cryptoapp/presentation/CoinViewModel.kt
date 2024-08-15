package com.hfad.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hfad.cryptoapp.data.network.model.CoinInfoDto
import com.hfad.cryptoapp.data.repository.CoinRepositoryImpl
import com.hfad.cryptoapp.domain.GetCoinInfoListUseCase
import com.hfad.cryptoapp.domain.GetCoinInfoUseCase
import com.hfad.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()


    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        //viewModelScope.launch {
        loadDataUseCase()
        //}
    }
}
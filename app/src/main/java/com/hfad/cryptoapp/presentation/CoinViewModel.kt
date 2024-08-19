package com.hfad.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import com.hfad.cryptoapp.domain.GetCoinInfoListUseCase
import com.hfad.cryptoapp.domain.GetCoinInfoUseCase
import com.hfad.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        //viewModelScope.launch {
        loadDataUseCase()
        //}
    }
}
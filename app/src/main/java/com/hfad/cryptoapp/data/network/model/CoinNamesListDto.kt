package com.hfad.cryptoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListDto (
    @SerializedName("Data")
    @Expose
    val data: List<CoinNameContainerDto>? = null
)
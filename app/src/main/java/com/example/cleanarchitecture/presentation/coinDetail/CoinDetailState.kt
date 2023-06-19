package com.example.cleanarchitecture.presentation.coinDetail

import com.example.cleanarchitecture.domin.model.CoinDetail

data class CoinDetailState(
    var isLoading: Boolean = false,
    var error :String ="",
    var coinDetail : CoinDetail ?= null
)
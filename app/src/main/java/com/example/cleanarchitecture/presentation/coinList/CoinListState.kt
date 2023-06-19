package com.example.cleanarchitecture.presentation.coinList

import com.example.cleanarchitecture.domin.model.Coin

data class CoinListState(
    var isLoading: Boolean = false,
    var error :String ="",
    var coinList : List<Coin> = emptyList()
)

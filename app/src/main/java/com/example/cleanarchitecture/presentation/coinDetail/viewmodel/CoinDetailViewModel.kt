package com.example.cleanarchitecture.presentation.coinDetail.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Constants
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domin.useCase.getCoins.GetCoinUseCase
import com.example.cleanarchitecture.presentation.coinDetail.CoinDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinDetailState = mutableStateOf(CoinDetailState())
    val coinDetailState: State<CoinDetailState> = _coinDetailState

    init {
       /* savedStateHandle.get<String>(Constants.COIN_ID)?.let { coinId ->
            getCoinList()
        }*/
        getCoinList()
    }

    private fun getCoinList() {
        getCoinUseCase("btc-bitcoin").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinDetailState.value = CoinDetailState(
                        coinDetail = result.data
                    )
                }

                is Resource.Loading -> {
                    _coinDetailState.value = CoinDetailState(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _coinDetailState.value = CoinDetailState(
                        error = result.message ?: "some went wrong"
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}
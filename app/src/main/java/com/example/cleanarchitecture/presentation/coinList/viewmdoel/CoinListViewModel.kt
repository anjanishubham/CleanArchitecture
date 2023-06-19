package com.example.cleanarchitecture.presentation.coinList.viewmdoel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domin.useCase.getCoins.GetCoinsUseCase
import com.example.cleanarchitecture.presentation.coinList.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
):ViewModel() {

    private val _coinListState = mutableStateOf(CoinListState())
    val coinListState:State<CoinListState> = _coinListState

    init {
        getCoinList()
    }
    private fun getCoinList() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinListState.value = CoinListState(
                        coinList = result.data ?: emptyList()
                    )
                }

                is Resource.Loading -> {
                    _coinListState.value = CoinListState(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _coinListState.value = CoinListState(
                        error = result.message ?: "some went wrong"
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}
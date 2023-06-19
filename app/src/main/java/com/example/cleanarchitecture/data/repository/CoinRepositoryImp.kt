package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.remote.CoinPaprikaApi
import com.example.cleanarchitecture.data.remote.dto.CoinDetailDto
import com.example.cleanarchitecture.data.remote.dto.CoinDto
import com.example.cleanarchitecture.domin.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinPaprikaApi.getCoinById(coinId)
    }
}
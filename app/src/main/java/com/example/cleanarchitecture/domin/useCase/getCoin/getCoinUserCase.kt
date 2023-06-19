package com.example.cleanarchitecture.domin.useCase.getCoins

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.remote.dto.toCoinDetail
import com.example.cleanarchitecture.domin.model.CoinDetail
import com.example.cleanarchitecture.domin.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(data = coin))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "some thing went wrong !!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Unable to reach server please check internet connection !!"))
        }
    }
}
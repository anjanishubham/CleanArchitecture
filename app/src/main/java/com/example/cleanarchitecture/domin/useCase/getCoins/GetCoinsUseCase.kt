package com.example.cleanarchitecture.domin.useCase.getCoins

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.remote.dto.toCoin
import com.example.cleanarchitecture.domin.model.Coin
import com.example.cleanarchitecture.domin.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        try {
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success(data = coins))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "some thing went wrong !!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Unable to reach server please check internet connection !!"))
        }
    }
}
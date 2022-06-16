package com.martin.currencyconverter.feature_currency.domain.use_cases

import com.martin.currencyconverter.feature_currency.domain.model.CCResponse
import com.martin.currencyconverter.feature_currency.domain.repository.CCRepository
import com.martin.currencyconverter.feature_currency.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCurrencyConversionResponse(
  private val ccRepository: CCRepository
) {
    suspend operator fun  invoke(amount : String, from : String, to : String ) : Resource<CCResponse> {
       return try {
           val response = ccRepository.currencyConvert(amount, from, to)
        val result = response.body()
        if(result?.success == true) {
            Resource.Success(result)
        } else {
            Resource.Error(response.message())
        }
    } catch(e: Exception) {
        Resource.Error(e.message ?: "An error occurred")
    }

    }
}
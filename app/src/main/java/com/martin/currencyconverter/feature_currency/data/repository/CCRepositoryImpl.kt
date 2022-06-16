package com.martin.currencyconverter.feature_currency.data.repository

import com.martin.currencyconverter.feature_currency.data.network.CurrencyService
import com.martin.currencyconverter.feature_currency.domain.model.CCResponse
import com.martin.currencyconverter.feature_currency.domain.repository.CCRepository
import retrofit2.Response
import javax.inject.Inject

class CCRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CCRepository {


    override suspend fun currencyConvert(
        amount: String,
        from: String,
        to: String
    ): Response<CCResponse> {
        return currencyService.currencyConvert(amount = amount, from = from, to = to)
    }

}
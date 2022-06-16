package com.martin.currencyconverter.feature_currency.data.network

import com.martin.currencyconverter.feature_currency.domain.model.CCResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/convert")
    suspend fun currencyConvert(
        @Query("amount") amount: String,
        @Query("from") from : String,
        @Query("to") to : String
    ): Response<CCResponse>


}
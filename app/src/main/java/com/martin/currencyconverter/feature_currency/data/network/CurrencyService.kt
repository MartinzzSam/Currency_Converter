package com.martin.currencyconverter.feature_currency.data.network

import com.martin.currencyconverter.feature_currency.domain.model.CCResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyService {

    @Headers("apikey: PYSXig87K0ELSbde1lN4cMmUWIZZbwv8")
    @GET("convert")
    suspend fun currencyConvert(
        @Query("amount") amount: String,
        @Query("from") from : String,
        @Query("to") to : String
    ): Response<CCResponse>


}
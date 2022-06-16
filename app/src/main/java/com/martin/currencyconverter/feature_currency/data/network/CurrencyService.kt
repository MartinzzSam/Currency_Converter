package com.martin.currencyconverter.feature_currency.data.network

import retrofit2.http.GET

interface CurrencyService {
    @GET("ada")
    suspend fun CurrencyConvert() {

    }
}
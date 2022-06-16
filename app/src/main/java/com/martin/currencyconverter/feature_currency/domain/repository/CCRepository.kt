package com.martin.currencyconverter.feature_currency.domain.repository

import com.martin.currencyconverter.feature_currency.domain.model.CCResponse
import retrofit2.Response

interface CCRepository {


    suspend fun currencyConvert(
        amount : String ,
        from : String ,
        to : String
    ) : Response<CCResponse>
}
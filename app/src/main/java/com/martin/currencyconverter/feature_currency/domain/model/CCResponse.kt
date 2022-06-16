package com.martin.currencyconverter.feature_currency.domain.model


import com.google.gson.annotations.SerializedName

data class CCResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("info")
    val info: Info,
    @SerializedName("query")
    val query: Query,
    @SerializedName("result")
    val result: Double,
    @SerializedName("success")
    val success: Boolean
)
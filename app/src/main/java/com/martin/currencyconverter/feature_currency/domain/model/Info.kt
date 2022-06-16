package com.martin.currencyconverter.feature_currency.domain.model


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("timestamp")
    val timestamp: Int
)
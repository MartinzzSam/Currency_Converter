package com.martin.currencyconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.martin.currencyconverter.R
import com.martin.currencyconverter.feature_currency.domain.model.CCResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
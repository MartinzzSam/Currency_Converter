package com.martin.currencyconverter.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.currencyconverter.feature_currency.domain.repository.CCRepository
import com.martin.currencyconverter.feature_currency.domain.use_cases.CCUseCases
import com.martin.currencyconverter.feature_currency.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CCViewModel @Inject constructor(
    private val ccUseCases: CCUseCases
) : ViewModel(){

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion



    fun convert(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ) {
        val fromAmount = amountStr.toFloatOrNull()
        if(fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = CurrencyEvent.Loading
            when(val ccUseCases = ccUseCases.getCCResponse.invoke(amountStr , fromCurrency , toCurrency)) {
                is Resource.Error-> _conversion.value = CurrencyEvent.Failure(ccUseCases.message!!)
                is Resource.Success -> {
                    val convertedCurrency = ccUseCases.data?.result
                        _conversion.value = CurrencyEvent.Success(
                            "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                        )
                    }
                }

            }
        }
    }

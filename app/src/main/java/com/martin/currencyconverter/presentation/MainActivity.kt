package com.martin.currencyconverter.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.martin.currencyconverter.R
import com.martin.currencyconverter.databinding.ActivityMainBinding
import com.martin.currencyconverter.feature_currency.domain.model.CCResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CCViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    binding.btnConvert.setOnClickListener {
        viewModel.convert(
            binding.etFrom.text.toString(),
            binding.spFromCurrency.selectedItem.toString(),
            binding.spToCurrency.selectedItem.toString(),
        )
    }

    lifecycleScope.launchWhenStarted {
        viewModel.conversion.collect { event ->
            when(event) {
                is CurrencyEvent.Success -> {
                    binding.progressBar.isVisible = false
                    binding.tvResult.setTextColor(Color.BLACK)
                    binding.tvResult.text = event.resultText
                }
                is CurrencyEvent.Failure -> {
                    binding.progressBar.isVisible = false
                    binding.tvResult.setTextColor(Color.RED)
                    binding.tvResult.text = event.errorText
                }
                is CurrencyEvent.Loading -> {
                    binding.progressBar.isVisible = true
                }
                else -> Unit
            }
        }
    }
}
}
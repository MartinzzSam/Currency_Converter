package com.martin.currencyconverter.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.martin.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: CCViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
            lifecycleScope.launchWhenStarted {
                viewModel.conversion.collect { event ->
                    Log.e("Error" , event.toString())
                    when (event) {
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


}

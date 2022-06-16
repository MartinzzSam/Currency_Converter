package com.martin.currencyconverter.di

import com.martin.currencyconverter.feature_currency.data.network.CurrencyService
import com.martin.currencyconverter.feature_currency.data.repository.CCRepositoryImpl
import com.martin.currencyconverter.feature_currency.domain.repository.CCRepository
import com.martin.currencyconverter.feature_currency.domain.use_cases.CCUseCases
import com.martin.currencyconverter.feature_currency.domain.use_cases.GetCurrencyConversionResponse
import com.martin.currencyconverter.feature_currency.domain.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyService::class.java)

    @Singleton
    @Provides
    fun  provideRepository(currencyService: CurrencyService) : CCRepository = CCRepositoryImpl(currencyService)


    @Singleton
    @Provides
    fun provideNoteUseCases(repository: CCRepository) : CCUseCases {
        return CCUseCases(
            getCCResponse = GetCurrencyConversionResponse(repository)
        )
    }

}
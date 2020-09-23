package com.example.stockapplication.di

import com.example.stockapplication.data.StockPriceService
import com.example.stockapplication.data.StockPriceServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiHelper(): StockPriceService = StockPriceServiceImpl()
}
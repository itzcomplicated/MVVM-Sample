package com.example.stockapplication.data

import com.example.stockapplication.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockPriceService {
    fun getStocks(): Flow<ArrayList<Stock>>
}
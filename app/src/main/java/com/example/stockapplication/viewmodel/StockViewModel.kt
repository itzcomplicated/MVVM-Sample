package com.example.stockapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapplication.data.StockPriceService
import com.example.stockapplication.view.StockView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class StockViewModel @ViewModelInject constructor(private val stockPriceService: StockPriceService) :
    ViewModel() {

    private val stocksView = MutableLiveData<ArrayList<StockView>>()

    fun getStocks(): LiveData<ArrayList<StockView>> {
        return stocksView
    }

    init {
        viewModelScope.launch {
            stockPriceService.getStocks()
                .collect { stocks ->
                    if(stocks.isNotEmpty()) {
                        val stockViewsToSet: ArrayList<StockView> = ArrayList(stocks.size)
                        stocks.forEach{ stock ->
                            stockViewsToSet.add(StockView(stock.id, stock.name, stock.priceInUsd.formatToUsd()))
                        }
                        stocksView.value = stockViewsToSet
                    }

                }
        }
    }

    private fun Double.formatToUsd() : String{
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("USD")
        return format.format(this)
    }
}

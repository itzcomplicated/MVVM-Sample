package com.example.stockapplication.data

import com.example.stockapplication.model.Stock
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class StockPriceServiceImpl : CoroutineScope, StockPriceService {

    companion object {
        const val NUMBER_OF_STOCKS = 10
        const val STOCK_EMIT_DELAY = 500L
    }

    private val job = Job()
    private val stocks: ArrayList<Stock> = ArrayList(NUMBER_OF_STOCKS)
    private val stockBroadcastChannel = BroadcastChannel<ArrayList<Stock>>(Channel.BUFFERED)

    init {
        (1..NUMBER_OF_STOCKS).forEach {
            stocks.add(Stock(it, "Stock_$it", getRandomPrice()))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun getStocks(): Flow<ArrayList<Stock>> {
        launch {
            stockUpdaterFlow().collect { stock ->
                stockBroadcastChannel.offer(stock)
            }
        }
        return stockBroadcastChannel.asFlow()
    }

    private fun getRandomPrice(): Double {
        return Random.nextDouble(100.0, 999.0)
    }

    private fun stockUpdaterFlow(): Flow<ArrayList<Stock>> = flow {
        while (true) {
            val stockToUpdateIndex = (0 until NUMBER_OF_STOCKS).random()
            val stockToUpdate = stocks[stockToUpdateIndex]
            stockToUpdate.priceInUsd = getRandomPrice()
            emit(stocks)
            delay(STOCK_EMIT_DELAY)
        }
    }

}
package com.example.stockprices.network

import com.example.stockprices.model.Stock
import dagger.Module
import javax.inject.Inject


class StockRepository @Inject constructor(private val apiService: StockApiService) {
    suspend fun getStocks(): List<Stock> = apiService.getStocks()
}
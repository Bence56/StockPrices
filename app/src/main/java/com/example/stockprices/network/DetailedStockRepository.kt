package com.example.stockprices.network

import com.example.stockprices.model.DetailedStock
import dagger.Module
import javax.inject.Inject

class DetailedStockRepository @Inject constructor(private val apiService: StockApiService) {
    suspend fun getStock(symbol:String): DetailedStock = apiService.getStock(symbol)
}
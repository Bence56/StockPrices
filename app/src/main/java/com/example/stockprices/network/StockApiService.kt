package com.example.stockprices.network

import com.example.stockprices.model.DetailedStock
import com.example.stockprices.model.Stock
import dagger.Module
import retrofit2.http.GET
import retrofit2.http.Path


interface StockApiService {
    @GET("stocks")
    suspend fun getStocks(): List<Stock>

    @GET("stocks/{symbol}")
    suspend fun getStock(@Path("symbol") symbol: String): DetailedStock

}
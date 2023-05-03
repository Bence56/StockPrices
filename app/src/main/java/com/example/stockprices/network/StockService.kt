package com.example.stockprices.network

import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {
    @GET("v1/data/quote")
    suspend fun getStockData(
        @Query("symbols") ticker: String,
        @Query("api_token") appId: String = "5dMTzQ47cSpSYruIB24smXSJQRFmQIk0sDnDYw7N"): StockResponse
}
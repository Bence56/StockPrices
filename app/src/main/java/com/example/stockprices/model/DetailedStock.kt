package com.example.stockprices.model

data class DetailedStock (
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val percentChange: Double,
    val marketCap: Double,
    val volume: Double,
    val imageUrl: String
)
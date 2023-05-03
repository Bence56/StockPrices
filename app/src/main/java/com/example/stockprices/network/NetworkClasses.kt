package com.example.stockprices.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class StockResponse(
    val meta:Meta,
    val data: List<Data>
)

@Serializable
data class Data (
    val ticker:String,
    val name:String,
    val exchange_short :String,
    val exchange_long:String,
    val mic_code:String,
    val currency:String,
    val price:Double,
    val day_high:Double,
    val day_low:Double,
    val day_open:Double,
    @SerialName("52_week_high")
    val year_week_high:Double,
    @SerialName("52_week_low")
    val year_week_low:Double,
    val market_cap:Number,
    val previous_close_price:Double,
    val previous_close_price_time: String,
    val day_change:Double,
    val volume:Int,
    val is_extended_hours_price:Boolean,
    val last_trade_time:String
)

@Serializable
data class Meta (
    val requested: Int,
    val returned: Int
)
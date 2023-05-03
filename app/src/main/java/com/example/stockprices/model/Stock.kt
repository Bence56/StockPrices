package com.example.stockprices.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="stocks")
data class Stock(
    @PrimaryKey
    @ColumnInfo(defaultValue = "STCK")
    val ticker: String,

    @ColumnInfo
    val price:Double
)

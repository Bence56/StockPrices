package com.example.stockprices.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockprices.model.Stock

@Database(entities = [Stock::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun stockDao(): StockDao
}
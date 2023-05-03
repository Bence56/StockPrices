package com.example.stockprices.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.stockprices.model.Stock
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

  @Query("SELECT * FROM stocks")
  fun getStocks(): Flow<List<Stock>>

  @Insert
  suspend fun insertStock(stock: Stock)

  @Delete
  suspend fun deleteStock(stock: Stock)

  @Update()
  fun updatePrice(stock:Stock)
}
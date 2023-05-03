package com.example.stockprices.model

import androidx.annotation.WorkerThread
import com.example.stockprices.persistance.StockDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepository @Inject constructor(
  private val stockDao: StockDao
){
  fun getAllStocks():Flow<List<Stock>> = stockDao.getStocks()

  suspend fun insertStock(stock: Stock){
    stockDao.insertStock(stock)
  }

  suspend fun deleteStock(stock: Stock) {
    stockDao.deleteStock(stock)
  }

  suspend fun updateStock(stock: Stock) {
    stockDao.updatePrice(stock)
  }

}
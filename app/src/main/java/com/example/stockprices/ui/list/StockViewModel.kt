package com.example.stockprices.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockprices.model.Stock
import com.example.stockprices.model.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
        private val stockRepository: StockRepository,
    ) : ViewModel() {

    val stocks: Flow<List<Stock>> = stockRepository.getAllStocks()

    suspend fun insertStock(stock: Stock){
        stockRepository.insertStock(stock = stock)
    }

    fun deleteStock(stock: Stock){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                stockRepository.deleteStock(stock = stock)
            }
        }
    }
}

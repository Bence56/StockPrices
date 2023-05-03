package com.example.stockprices.ui.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavBackStackEntry
import com.example.stockprices.model.Stock
import com.example.stockprices.model.StockRepository
import com.example.stockprices.network.StockResponse
import com.example.stockprices.network.StockService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface StockUiState {
    data class Success(val stockResult: StockResponse) : StockUiState
    object Error : StockUiState
    object Loading : StockUiState
}
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val stockService: StockService,
    private val stockRepository: StockRepository
) : ViewModel() {

    var weatherUiState: StockUiState by mutableStateOf(StockUiState.Loading)

        fun getWeather(cityName: String) {
            viewModelScope.launch {
                weatherUiState = try {
                    val result = stockService.getStockData(cityName)
                    var stock:Stock = Stock (result.data.get(0).ticker,result.data.get(0).price)
                    updateStock(stock)
                    StockUiState.Success(result)
                } catch (e: IOException) {
                    StockUiState.Error
                } catch (e: HttpException) {
                    StockUiState.Error
                }
            }
        }

        suspend fun updateStock(stock: Stock){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    stockRepository.updateStock(stock = stock)
                }
            }
        }
    }

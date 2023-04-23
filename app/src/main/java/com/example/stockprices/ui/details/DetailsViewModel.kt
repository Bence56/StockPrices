package com.example.stockprices.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockprices.model.DetailedStock
import com.example.stockprices.network.DetailedStockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailedStockRepository) : ViewModel() {

    private val _detailedstock = MutableLiveData<DetailedStock>()
    val detailedstock: LiveData<DetailedStock>
        get() = _detailedstock

    fun getStockDetails(symbol: String) {
        viewModelScope.launch {
            try {
                val result = repository.getStock(symbol)
                _detailedstock.value = result
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}
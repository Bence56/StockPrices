package com.example.stockprices.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockprices.model.Stock
import com.example.stockprices.network.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: StockRepository) : ViewModel() {
    val stocks: MutableLiveData<List<Stock>> = MutableLiveData()

    init {
        viewModelScope.launch {
            stocks.value = repository.getStocks()
        }
    }
}
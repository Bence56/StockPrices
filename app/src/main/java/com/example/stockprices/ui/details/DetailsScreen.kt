package com.example.stockprices.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockprices.network.StockResponse

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    when (detailsViewModel.weatherUiState) {
        is StockUiState.Loading
        -> LoadingScreen(modifier)
        is StockUiState.Success -> ResultScreen((detailsViewModel.weatherUiState as StockUiState.Success).stockResult, modifier)
        is StockUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.size(200.dp),
            text = "Loading..."
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Error...")
    }
}

@Composable
fun ResultScreen(weatherResult: StockResponse, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(getStockDetails(weatherResult))
    }
}

fun getStockDetails(weatherResult: StockResponse) : String {

    val name:String = weatherResult.data.get(0).name
    val index:String = weatherResult.data.get(0).exchange_short
    val ticker:String = weatherResult.data.get(0).ticker
    val currency:String = weatherResult.data.get(0).currency
    val daychange: Double = weatherResult.data.get(0).day_change
    val volume: Int = weatherResult.data.get(0).volume
    val price: Double = weatherResult.data.get(0).price

    val result =  """
        Company name: $name
        Ticker: $ticker
        Currency: $currency
        Exchange: $index
        Day change: $daychange%
        Volume: $volume
        Price: $price
        
    """.trimIndent()
    return result

}
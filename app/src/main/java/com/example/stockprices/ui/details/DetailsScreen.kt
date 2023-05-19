package com.example.stockprices.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockprices.network.StockResponse

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    when (detailsViewModel.weatherUiState) {
        is StockUiState.Loading
        -> LoadingScreen(modifier)
        is StockUiState.Success -> ResultScreen((detailsViewModel.weatherUiState as StockUiState.Success).stockResult, modifier,navController)
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
fun ResultScreen(stockResponse: StockResponse,
                 modifier: Modifier = Modifier,
                 navController:NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextRow(label = "Company:", value = stockResponse.data.get(0).name)
        TextRow(label = "Ticker:", value = stockResponse.data.get(0).ticker)
        TextRow(label = "Price:", value = stockResponse.data.get(0).price.toString())
        TextRowDayChange(label = "Daily change:", value = stockResponse.data.get(0).day_change)
        TextRow(label = "Day open:", value = stockResponse.data.get(0).day_open.toString())
        TextRow(label = "Daily low:", value = stockResponse.data.get(0).day_low.toString())
        TextRow(label = "Daily high:", value = stockResponse.data.get(0).day_high.toString())
        TextRow(label = "Market cap:", value = stockResponse.data.get(0).market_cap.toString())
        TextRow(label = "Volume:", value = stockResponse.data.get(0).volume.toString())
        TextRow(label = "Index:", value = stockResponse.data.get(0).exchange_short)
        TextRow(label = "Currency:", value = stockResponse.data.get(0).currency)

        Button(onClick = { navController.navigate("list") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)) {
            Text(text = "Back")
        }
    }


}

@Composable
fun TextRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
    }
}
@Composable
fun TextRowDayChange(label: String, value: Double) {
    val textColor = if (value < 0) {
        Color.Red
    } else if(value == 0.0) {
        Color.Black
    }
    else{
        Color.Green
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value.toString() + " %",
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End,
            color = textColor
        )
    }
}
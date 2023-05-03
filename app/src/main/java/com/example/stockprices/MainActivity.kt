package com.example.stockprices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockprices.ui.details.DetailsViewModel
import com.example.stockprices.ui.list.StockViewModel
import com.example.stockprices.ui.navigation.StockPricesNavHost
import com.example.stockprices.ui.theme.StockPricesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listViewModel:StockViewModel by viewModels()
        val detailsViewModel:DetailsViewModel by viewModels()

        setContent {
            StockPricesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StockPricesNavHost(listViewModel = listViewModel, detailsViewModel = detailsViewModel)

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPricesTheme {
        Greeting("Android")
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.stockprices.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.*
import com.example.stockprices.model.Stock
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listViewModel: StockViewModel,
    navController: NavController
){
    val coroutineScope = rememberCoroutineScope()
    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val list2: Flow<List<Stock>> = listViewModel.stocks

    val stockList by list2.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Stocks")
                },
                actions = {

                })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add ticker",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            LazyColumn() {
                items(stockList) {
                    StockCard(it.ticker,it.price,
                        removeItem = {
                            listViewModel.deleteStock(it)
                        },
                        itemClick = {
                            it -> navController.navigate("stock/$it")
                        }
                    )
                }
            }
            if (showAddDialog) {
                AddNewCityForm(
                    addNewCity = { cityName ->
                        coroutineScope.launch {
                            listViewModel.insertStock(Stock(cityName,0.0))
                        }
                    },
                    dialogDismiss = { showAddDialog = false })
            }
        }
    }
}


@Composable
fun AddNewCityForm(
    addNewCity: (String) -> Unit,
    dialogDismiss: () -> Unit
) {
    Dialog(onDismissRequest = dialogDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                var ticker by remember {
                    mutableStateOf("")
                }

                OutlinedTextField(
                    value = ticker,
                    label = { Text(text = "Ticker") },
                    onValueChange = {
                        ticker = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(onClick = {
                    addNewCity(ticker)
                    dialogDismiss()
                }) {
                    Text(text = "Add stock")
                }
            }
        }
    }
}

@Composable
fun StockCard(
    ticker: String,
    price: Double,
    removeItem: () -> Unit,
    itemClick: (String) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp).clickable {
            itemClick(ticker)
        }
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                modifier = Modifier
                    .weight(1f),
                text = ticker
            )

            Text(
                modifier = Modifier.weight(2f),
                text= price.toString()
            )

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",

                modifier = Modifier.clickable {
                    removeItem()
                },
                tint = Color.Red
            )

        }
    }
}
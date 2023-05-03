package com.example.stockprices.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockprices.ui.details.DetailsViewModel
import com.example.stockprices.ui.details.DetailsScreen
import com.example.stockprices.ui.list.ListScreen
import com.example.stockprices.ui.list.StockViewModel

@Composable
fun StockPricesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list",
    listViewModel: StockViewModel,
    detailsViewModel: DetailsViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list") { ListScreen(modifier=modifier, listViewModel = listViewModel,navController=navController) }

        composable("weather/{ticker}",
            arguments = listOf(
                navArgument("ticker"){type = NavType.StringType}
            )
        ) {
            val ticker = it.arguments?.getString("ticker")
            if (ticker != null) {
                detailsViewModel.getWeather(ticker)
            }
            ticker?.let {
                DetailsScreen(modifier=modifier, detailsViewModel=detailsViewModel)
            }
        }
    }
}
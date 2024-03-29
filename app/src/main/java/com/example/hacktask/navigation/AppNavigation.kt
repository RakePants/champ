package com.example.hacktask.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hacktask.features.add_order.AddOrderScreen
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.order.OrderScreen
import com.example.hacktask.features.order.presentation.OrderViewModel
import com.example.hacktask.features.order_description.OrderDescriptionScreen
import com.example.hacktask.features.order_description.presentation.OrderDescriptionViewModel

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    val orderViewModel: OrderViewModel = hiltViewModel()
    val addOrderViewModel: AddOrderViewModel = hiltViewModel()
    val descriptionViewModel: OrderDescriptionViewModel = hiltViewModel()

    NavHost (
        navController = navHostController,
        startDestination = Screens.OrderScreen.route
    ) {
        composable(route = Screens.OrderScreen.route) {
            OrderScreen(
                viewModel = orderViewModel,
                navHostController = navHostController
            )
        }
        composable(route = Screens.AddOrderScreen.route) {
            AddOrderScreen(
                viewModel = addOrderViewModel,
                navHostController = navHostController,
            )
        }
        composable(
            route = "${Screens.DescriptionScreen.route}/{orderId}",
            arguments = listOf(
                navArgument("orderId") { type = NavType.StringType }
            )
        ) {
            OrderDescriptionScreen(
                viewModel = descriptionViewModel,
                navHostController = navHostController,
                id = it.arguments?.getString("orderId")?.toInt() ?: throw Exception("Id not found")
            )
        }
    }
}

package com.example.hacktask.features.add_order

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel

@Composable
fun AddOrderScreen(
    viewModel: AddOrderViewModel,
    navHostController: NavHostController,
    //id: Int,
) {
    AddOrderBasic(
        viewModel = viewModel,
        navHostController = navHostController,
    )
}
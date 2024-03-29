package com.example.hacktask.features.order

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktask.features.order.presentation.OrderScreenState
import com.example.hacktask.features.order.presentation.OrderSideEffect
import com.example.hacktask.features.order.presentation.OrderViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OrderScreen(
    viewModel: OrderViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect
        )
    }
    OrderBasic(
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffect(
    viewModel: OrderViewModel,
    sideEffect: OrderSideEffect
) {
    when (sideEffect) {
        is OrderSideEffect.ShowLoading -> {
            viewModel.screenState.value = OrderScreenState.Loading
        }
        is OrderSideEffect.ShowError -> {
            viewModel.screenState.value = OrderScreenState.Error
        }
        is OrderSideEffect.ShowSuccess -> {
            viewModel.screenState.value = OrderScreenState.Success
        }
    }
}
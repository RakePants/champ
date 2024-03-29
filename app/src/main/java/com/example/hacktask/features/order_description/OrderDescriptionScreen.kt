package com.example.hacktask.features.order_description

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktask.features.order_description.presentation.OrderDescriptionScreenState
import com.example.hacktask.features.order_description.presentation.OrderDescriptionSideEffect
import com.example.hacktask.features.order_description.presentation.OrderDescriptionViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OrderDescriptionScreen(
    viewModel: OrderDescriptionViewModel,
    navHostController: NavHostController,
    id : Int,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect,
            id = id
        )
    }
    OrderDescriptionBasic(
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffect(
    viewModel: OrderDescriptionViewModel,
    sideEffect: OrderDescriptionSideEffect,
    id: Int,
) {
    when (sideEffect) {
        OrderDescriptionSideEffect.ShowError -> {
            viewModel.screenState.value = OrderDescriptionScreenState.Error
        }
        OrderDescriptionSideEffect.ShowLoading -> {
            viewModel.getIdForResponse(id = id)
            Log.d("Test", "handleSideEffect: ${id}")
            viewModel.screenState.value = OrderDescriptionScreenState.Loading
        }
        OrderDescriptionSideEffect.ShowSuccess -> {
            viewModel.screenState.value = OrderDescriptionScreenState.Success
        }
    }
}
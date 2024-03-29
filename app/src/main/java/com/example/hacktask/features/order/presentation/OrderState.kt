package com.example.hacktask.features.order.presentation

import com.example.hacktask.model.Order

data class OrderState(
    val listOfOrders: List<Order> = listOf<Order>(),
)

sealed class OrderScreenState {
    object Loading : OrderScreenState()
    object Error : OrderScreenState()
    object Success : OrderScreenState()
}
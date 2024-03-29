package com.example.hacktask.features.order.presentation

sealed class OrderSideEffect {
    object ShowLoading: OrderSideEffect()
    object ShowSuccess: OrderSideEffect()
    object ShowError: OrderSideEffect()
}
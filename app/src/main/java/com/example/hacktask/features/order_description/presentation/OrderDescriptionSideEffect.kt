package com.example.hacktask.features.order_description.presentation

import com.example.hacktask.features.order.presentation.OrderSideEffect

sealed class OrderDescriptionSideEffect {
    object ShowLoading: OrderDescriptionSideEffect()
    object ShowSuccess: OrderDescriptionSideEffect()
    object ShowError: OrderDescriptionSideEffect()
}
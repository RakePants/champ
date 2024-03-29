package com.example.hacktask.features.order_description.presentation

data class OrderDescriptionState(
    val id: Int = 0,
    val latitude: Float = 0.0f,
    val longitude: Float = 0.0f,
    val address: String = "",
    val description: String = "",
    val timestamp: String = "",
    val type: String = "",
    val volume: Int = 0,
    val status: String = "",
    val image: String = "",
    val completionImage: String? = null,
    val completionTimestamp: String? = null
)

sealed class OrderDescriptionScreenState {
    object Loading : OrderDescriptionScreenState()
    object Error : OrderDescriptionScreenState()
    object Success : OrderDescriptionScreenState()
}


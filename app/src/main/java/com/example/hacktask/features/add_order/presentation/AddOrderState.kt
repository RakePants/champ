package com.example.hacktask.features.add_order.presentation

import android.graphics.Bitmap

data class AddOrderState(
    val address: String = "",
    val type: String = "",
    val description: String = "",
    val lattitude: Double? = null,
    val longitude: Double? = null,
    val volume: String = "",
    val photo: Bitmap? = null,
)
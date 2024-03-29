package com.example.hacktask.data.model

data class OrderEntity(
    val address: String,
    val completion_image: String,
    val completion_target_date: String,
    val completion_timestamp: String,
    val contractor_id: String,
    val description: String,
    val id: String,
    val latitude: Float,
    val longitude: Float,
    val original_image: String,
    val processed_image: String,
    val status: String,
    val timestamp: String,
    val type: String,
    val volume: Int
)
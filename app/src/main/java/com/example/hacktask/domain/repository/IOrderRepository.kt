package com.example.hacktask.domain.repository

import com.example.hacktask.model.Order
import retrofit2.Call
import java.io.File

interface IOrderRepository {
    suspend fun createOrder(
        latitude: Double,
        longitude: Double,
        address: String,
        description: String,
        type: String,
        volume: Int,
        imageFile: File,
    )
    suspend fun getAllOrders(): List<Order>
    suspend fun getOrderById(id: String): Order
}
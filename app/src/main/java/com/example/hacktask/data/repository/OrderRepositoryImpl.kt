package com.example.hacktask.data.repository

import com.example.hacktask.data.remote.OrderService
import com.example.hacktask.domain.repository.IOrderRepository
import com.example.hacktask.model.Order
import javax.inject.Inject

class OrderRepositoryImpl (
    val orderService: OrderService
) : IOrderRepository {
    override suspend fun createOrder(order: Order) {
        orderService.createOrder(order)
    }

    override suspend fun getAllOrders(): List<Order> {
        return orderService.getAllOrders()
    }

    override suspend fun getOrderById(id: String): Order {
        return orderService.getOrderById(id)
    }
}
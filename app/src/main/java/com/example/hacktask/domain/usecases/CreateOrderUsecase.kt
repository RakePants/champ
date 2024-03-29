package com.example.hacktask.domain.usecases

import com.example.hacktask.data.repository.OrderRepositoryImpl
import com.example.hacktask.domain.repository.IOrderRepository
import com.example.hacktask.model.Order

class CreateOrderUsecase(
    private val repository: IOrderRepository
) {
    suspend fun execute(order: Order) {
        repository.createOrder(
            latitude = order.latitude.toDouble(),
            longitude = order.longitude.toDouble(),
            address = order.address,
            description = order.description,
            type = order.type,
            volume = order.volume,
            imageFile = order.image
        )
    }
}
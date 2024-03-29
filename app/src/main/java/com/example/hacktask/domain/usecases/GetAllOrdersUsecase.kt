package com.example.hacktask.domain.usecases

import com.example.hacktask.model.Order
import kotlinx.coroutines.delay

class GetAllOrdersUsecase(
    //repository
) {
    suspend fun execute(): List<Order> {
        val tempList = mutableListOf<Order>()
        for (i in 1..10) {
            tempList.add(
                Order(
                    id = i,
                    latitude = 1.124F,
                    longitude = 2.224F,
                    address = "Москва, ул. Пушкина, д. 17",
                    description = "Хыть",
                    timestamp = "12.12.2022",
                    type = "Автобус",
                    volume = 5,
                    status = "В процессе",
                    completionImage = "???",
                    completionTimestamp = "12.12.2022",
                    image = "???"
                )
            )
        }
        delay(2000)
        return tempList
    }
}
package com.example.hacktask.domain.usecases

import com.example.hacktask.model.Order
import kotlinx.coroutines.delay

class GetOrderUsecase(
    //repository
) {
    suspend fun execute(id: Int): Order {
        val order = Order(
            id = 5,
            latitude = 1.124F,
            longitude = 2.224F,
            address = "Москва, ул. Пушкина, д. 17",
            description = "Хыть пупупуу боооольщое описание, бла бла бла бла бла бла бла бла бла бла блаб лаб лаб лаб лаб лаб",
            timestamp = "12.12.2022",
            type = "Автобус",
            volume = 5,
            status = "В процессе",
            completionImage = "???",
            completionTimestamp = "03.01.2023",
            image = "???"
        )
        delay(3000)
        return order
    }
}
package com.rusgorprojects.bookshareapp.model

import java.time.LocalDateTime

data class OrderResponse (
        val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,
        //orderStatus als enum
        val status: OrderStatus ,
        val orderPositions: List<OrderPositionResponse>
)

enum class OrderStatus {
        NEW, CONFIRMED, SENT, DELIVERED, CANCELED
}

data class OrderPositionResponse(
        val id: String,
        val bookId: String,
        val quantity: Long
)
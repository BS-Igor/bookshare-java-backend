package com.rusgorprojects.bookshareapp.repository

import com.rusgorprojects.bookshareapp.model.OrderCreateRequest
import com.rusgorprojects.bookshareapp.model.OrderResponse
import com.rusgorprojects.bookshareapp.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


 @Service
class OrderRepository {

    val orders = mutableListOf<OrderResponse>()

    fun save(request: OrderCreateRequest): OrderResponse {
    val orderResponse = OrderResponse(
            id = UUID.randomUUID().toString(),
            customerId = request.customerId,
            orderTime = LocalDateTime.now(),
            status = OrderStatus.NEW,
            orderPositions = emptyList()
    )
        //in Kotlin versucht man immutable zu programmieren
        orders.add(orderResponse)
        return orderResponse
    }

    fun findById(orderId: String): OrderResponse? { // nullable OrderResponse. Entweder OrderResponse oder nichts
        return orders.find { it.id == orderId }
    }

}

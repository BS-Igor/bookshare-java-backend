package com.rusgorprojects.bookshareapp.repository

import com.rusgorprojects.bookshareapp.model.OrderCreateRequest
import com.rusgorprojects.bookshareapp.model.OrderResponse
import com.rusgorprojects.bookshareapp.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


 @Service
class OrderRepository {

    private val orders = mutableListOf<OrderResponse>()

    fun save(orderResponse: OrderResponse): OrderResponse {

        //in Kotlin versucht man immutable zu programmieren
        orders.add(orderResponse)
        return orderResponse
    }

    fun findById(orderId: String): OrderResponse? { // nullable OrderResponse. Entweder OrderResponse oder nichts
        return orders.find { it.id == orderId }
    }

     fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderResponse> {
         return orders.filter{ it.customerId == customerId && it.status == OrderStatus.NEW }
     }

 }

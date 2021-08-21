package com.rusgorprojects.bookshareapp.service

import com.rusgorprojects.bookshareapp.model.*
import com.rusgorprojects.bookshareapp.repository.BookRepository
import com.rusgorprojects.bookshareapp.repository.CustomerRepository
import com.rusgorprojects.bookshareapp.repository.OrderPositionRepository
import com.rusgorprojects.bookshareapp.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService (

    val orderRepository: OrderRepository,
    val orderPositionRepository: OrderPositionRepository,
    val customerRepository: CustomerRepository,
    val bookRepository: BookRepository

) {
    fun createOrder(request: OrderCreateRequest): OrderResponse {

        val customer: CustomerResponse = customerRepository.findById(request.customerId)
                ?: throw Exception("Customer not found")                    // "?:" Der so genannte Elvis Operator. Wenn vor dem Elvis Operator nicht null ist, dann gib es aus. Wenn es null ist, dann gib aus, was rechts vom Elvis Operator steht

        return orderRepository.save(request)
    }

    fun createNewPositionForOrder(
            orderId: String,
            request: OrderPositionCreateRequest
    ): OrderPositionResponse {

        orderRepository.findById(orderId) ?:
            throw Exception("Order not found")

        if(bookRepository.findById(request.bookId).isEmpty)
            throw Exception("Book not found")

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                bookId = request.bookId,
                quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }


}
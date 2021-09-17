package com.rusgorprojects.bookshareapp.service

import com.rusgorprojects.bookshareapp.exceptions.BookshareException
import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException
import com.rusgorprojects.bookshareapp.model.*
import com.rusgorprojects.bookshareapp.repository.BookRepository
import com.rusgorprojects.bookshareapp.repository.CustomerRepository
import com.rusgorprojects.bookshareapp.repository.OrderPositionRepository
import com.rusgorprojects.bookshareapp.repository.OrderRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderService (

    val orderRepository: OrderRepository,
    val orderPositionRepository: OrderPositionRepository,
    val customerRepository: CustomerRepository,
    val bookRepository: BookRepository

) {
    fun createOrder(request: OrderCreateRequest): OrderResponse {

        customerRepository.findById(request.customerId)

        val orderResponse = OrderResponse(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTime = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositions = emptyList()
        )

        return orderRepository.save(orderResponse)
    }

    fun createNewPositionForOrder(
            orderId: String,
            request: OrderPositionCreateRequest
    ): OrderPositionResponse {

        orderRepository.findById(orderId) ?:
       throw IdNotFoundException(
               message = "Order with id $orderId not found",
               statusCode = HttpStatus.BAD_REQUEST)

        if(bookRepository.findById(request.bookId).isEmpty)
            throw BookshareException(
                    message = "Book with id ${request.bookId} not found",
                    statusCode = HttpStatus.BAD_REQUEST)

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                bookId = request.bookId,
                quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }

    fun updateOrder(id: String, request: OrderUpdateRequest): OrderResponse {
        // val ist die unveränderliche Variante einer Variable (kein var)
        val order = orderRepository.findById(id) ?: throw IdNotFoundException("Order with id $id not found")

        val updatedOrder = order.copy(
                status = request.orderStatus ?: order.status
        )

        return orderRepository.save(updatedOrder)
    }


}
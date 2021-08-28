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
                ?: throw BookshareException(
                        message = "Customer with id ${request.customerId} not found",
                        statusCode = HttpStatus.BAD_REQUEST)                    // "?:" Der so genannte Elvis Operator. Wenn vor dem Elvis Operator nicht null ist, dann gib es aus. Wenn es null ist, dann gib aus, was rechts vom Elvis Operator steht

        return orderRepository.save(request)
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
                bookId = request.bookId,
                quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }


}
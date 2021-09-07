package com.rusgorprojects.bookshareapp.service

import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException
import com.rusgorprojects.bookshareapp.model.BookResponse
import com.rusgorprojects.bookshareapp.model.OrderPositionResponse
import com.rusgorprojects.bookshareapp.model.OrderResponse
import com.rusgorprojects.bookshareapp.model.ShoppingCartResponse
import com.rusgorprojects.bookshareapp.repository.BookRepository
import com.rusgorprojects.bookshareapp.repository.OrderPositionRepository
import com.rusgorprojects.bookshareapp.repository.OrderRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class ShoppingCartService(
        val orderRepository: OrderRepository,
        val orderPositionRepository: OrderPositionRepository,
        val bookRepository: BookRepository
) {

    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderResponse> = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId)

        val orderIds = orders.map { it.id }

        val orderPositions: List<OrderPositionResponse> = orderPositionRepository.findAllByOrderIds(orderIds)

        val deliveryCost = 800L //TODO: feature to select delivery method?
        val totalAmount: Long = calculateSumForCart(orderPositions, deliveryCost)

        return ShoppingCartResponse(
                customerId = customerId,
                orderPositions = orderPositions,
                deliveryOption = "STANDARD",
                deliveryCostInCent = deliveryCost,
                totalAmountInCent = totalAmount
        )
    }

    fun calculateSumForCart(    //"privat" für das Testing entfernt
            orderPositions: List<OrderPositionResponse>,
            deliveryCost: Long
    ): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val book: BookResponse = bookRepository
                    .findById(it.bookId)
                    .orElseThrow {
                        throw IdNotFoundException("Book with id ${it.bookId} not found")}
            if(it.quantity <= 0)
                throw IllegalArgumentException("OrderPosition with quantity of ${it.quantity} is not allowed.")
            it.quantity * book.priceInCent
        }
        val positionSum = positionAmounts.sumBy { it.toInt() }

        val totalAmount: Long = positionSum + deliveryCost
        return  totalAmount
    }

}

package com.rusgorprojects.bookshareapp.controller

import com.rusgorprojects.bookshareapp.exceptions.BookshareException
import com.rusgorprojects.bookshareapp.model.OrderCreateRequest
import com.rusgorprojects.bookshareapp.model.OrderPositionCreateRequest
import com.rusgorprojects.bookshareapp.model.OrderResponse
import com.rusgorprojects.bookshareapp.model.OrderUpdateRequest
import com.rusgorprojects.bookshareapp.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(
        val orderService: OrderService
) {


    @PostMapping("/orders")
    fun createOrder(
            @RequestBody request: OrderCreateRequest
    ): OrderResponse {
            return orderService.createOrder(request)
    }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
            @PathVariable(name = "id") orderId: String,
            @RequestBody request: OrderPositionCreateRequest
    ) {
        orderService.createNewPositionForOrder(orderId, request)
    }

    @PutMapping("/orders/{id}")
    fun updateOrder(
            @PathVariable id: String,
            @RequestBody request: OrderUpdateRequest
    ){
        orderService.updateOrder(id, request)
    }
}
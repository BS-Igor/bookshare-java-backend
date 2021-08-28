package com.rusgorprojects.bookshareapp.exceptions

import org.springframework.http.HttpStatus

class BookshareException(
        override val message: String,
        val statusCode: HttpStatus
): RuntimeException(message)

class IdNotFoundException(
        override val message: String,
        val statusCode: HttpStatus
): RuntimeException(message)
package com.rusgorprojects.bookshareapp.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import java.net.http.HttpRequest
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(Throwable::class) // ExceptionHandler, der eine beliebige Art von Throwable abfängt
    fun handleErrors(request: HttpServletRequest, exception: Throwable) :ResponseEntity<ErrorInfo> {

        println(exception.message)
        println(exception)

        val (code, message) = when(exception) {
            is BookshareException -> exception.statusCode to exception.message
            is IdNotFoundException -> HttpStatus.BAD_REQUEST to exception.message // spezifische Exception hier einfach hinzufügen
            is IllegalArgumentException -> HttpStatus.BAD_REQUEST to (exception.message ?: "Illegal Argument")
            else -> HttpStatus.INTERNAL_SERVER_ERROR to (exception.message ?: "An errror occured")
        }

        val errorInfo = ErrorInfo(message, request.requestURI)
        return ResponseEntity(errorInfo, code)
    }
    //HttpStatus.INTERNAL_SERVER_ERROR ist für unerwartete Exceptions. Die erwarteten Exceptions werden an anderer Stelle behandelt.

    data class ErrorInfo(
            val error: String,
            val path: String
    )

}
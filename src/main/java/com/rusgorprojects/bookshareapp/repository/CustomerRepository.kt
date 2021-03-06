package com.rusgorprojects.bookshareapp.repository

import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException
import com.rusgorprojects.bookshareapp.model.CustomerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service

class CustomerRepository {
    //listof() ist eine Liste von Objekten
    val customers = listOf(
            CustomerResponse(
                    "1",
                    "Beatriz",
                    "Sorrentino",
                    "biasorrentino@hotmail.com"
            )
    )

    //"CustomerResponse?" bedeutet, es kommt als Returnwert entweder "CustomerResponse" oder Null
    fun findById(id: String): CustomerResponse {

        //Lambda-Funktion wird in geschweifte Klammern geschrieben
        //statt ".getId()" kann man direkt ".id" schreiben
        // statt ".equals" kann man "==" verwenden
        //.find {c -> c.id == id } Kurzschreibweise: .find {it.id == id }
        //.find gibt das erste Element zurück, das übereinstimmt
        return customers.find { it.id == id }
                ?: throw IdNotFoundException("Customer with id $id not found")
    }
}
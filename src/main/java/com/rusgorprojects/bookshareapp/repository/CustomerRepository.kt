package com.rusgorprojects.bookshareapp.repository

import com.rusgorprojects.bookshareapp.model.CustomerResponse
import java.util.*

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
    fun findById(id: String): CustomerResponse? {

        //Lambda-Funktion wird in geschweifte Klammern geschrieben
        //statt ".getId()" kann man direkt ".id" schreiben
        // statt ".equals" kann man "==" verwenden
        //.find {c -> c.id == id } Kurzschreibweise: .find {it.id == id }
        //.find gibt das erste Element zurück, das übereinstimmt
         val customer = customers.find { it.id == id }
        return customer
    }
}
package com.rusgorprojects.bookshareapp.controller

import com.rusgorprojects.bookshareapp.model.CustomerResponse
import com.rusgorprojects.bookshareapp.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
//wieder braucht man hier nicht "public" zu schreiben
//keine Data-Class, wie "CustomerResponse"
// Controller, Repositories und Services sind die Klassen, die meine Logik enthalten

class CustomerController(
        //val ist final, unveränderbar
        //kein "new" nötig für einen Konstruktoraufruf
        val customerRepository: CustomerRepository
) {

    @GetMapping("/customers/{id}")
    //fun steht für function
    fun getCustomerById(@PathVariable id:String):ResponseEntity<CustomerResponse>{
         val customer =  customerRepository.findById(id)
        //Kurzschreibweise falls
        return if(customer != null)
              ResponseEntity.ok(customer)
            else
                ResponseEntity.notFound().build()
    }
}
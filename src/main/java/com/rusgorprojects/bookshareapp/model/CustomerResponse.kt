package com.rusgorprojects.bookshareapp.model
// public ist implizit wenn man keinen Zugriffsmodifikatoren verwendet (automatisch)
// Konstruktor kommt direkt hinter den Klassennamen
// neue Notation bei Deklarationen (umgedreht)
class CustomerResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
        // bei "val" die Getter Methoden sind implizit da, Setter Methoden werden NICHT zur Verfügung gestellt
        // bei "var" werden
){

}
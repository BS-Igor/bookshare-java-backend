- Pfad
- Query Paramter (Alle hinter dem "?" )
- HTTP Verb: GET, POST, PUT, DELETE (HEAD, OPTIONS, PATCH, TRACE)
- Request Body (wird bei Formularen übergeben)

#### REST: in Ressourcen denken, nicht in Entdpunkte denken
 
- Books
- Kunden
- Bestellungen

####Lade alle Bücher vom Server:
GET /api/books

####Lade Buch mit bestimmter ID:
GET /api/books/{id}

####Erzeuge ein Buch:
POST /api/books
https://youtu.be/T41C_rzC-uw?list=PLrW9Tlul4Cf-IG40I6uuX8tojzsBvkGx8&t=1790


---
Lade Buch mit bestimmtem tag:
GET /api/books?tags={tag}

Füge tags zu Buch hinzu:
POST /api/books/{id}/tags

Ändere Buch:
PUT /api/books/

Erzeuge ein Buch:
POST /api/books
https://youtu.be/T41C_rzC-uw?list=PLrW9Tlul4Cf-IG40I6uuX8tojzsBvkGx8&t=1790
 
Lösche ein Buch:
DELETE /api/books?id={id}

Erstelle Bestellung:
POST /api/orders

Füge Produkt zu Bestellung hinzu
PUT /api/orders/{id}/books


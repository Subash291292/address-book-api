# Address Book (Backend application)

Following features are developed as part of this assignment,

  Address book should hold name and phone number of contact entries

  Users should be able to add a new contact entry to an existing address book
  
  Users should be able to retrieve a unique set of all contacts
 
# Assumptions

 There are already two existing address books initialised in memory
 
 Design & implement RESTful API services to meet the above requirements
 
 Data should only be persisted in memory
 
 Deployment is NOT required

# Note that it is not a production qualiity code but just a demonstration of a few aspects of

  Java
  
  Spring boot
  
  REST
  
  Validation
  
  Unit Testing

# How to start and use

Build

  mvn clean install

Test

  mvn test

Start

  mvn springboot:run

if application server is not starting with above comments please try to run the application by local IDE

# REST ENDPOINTS

Add Contact

 POST http://localhost:8080/api/v1/address-book/melbourne/contacts

 Payload:
 {
   "name": "Afsal",
   "phoneNumber": "+61481303517"
 }

Get Unique Contacts

 GET http://localhost:8080//api/v1/address-book/contacts?unique=true

Get All Contacts

 GET http://localhost:8080//api/v1/address-book/contacts?unique=false


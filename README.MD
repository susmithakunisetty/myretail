1. Case Study : myRetail RESTful service

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
Example product IDs: 13860428, 54456119, 13264003, 12954218) 
•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. ?
•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

===================================================================

Solution :

Tech Stack : 
1. Spring Boot
2. Java 8
3. Mongo DB - Docker Container
4. Maven


API Documentation : http://localhost:8080/swagger-ui.html

APIs : 
1. HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number). 

Request: GET http://localhost:8080/products/13860428
Response : {"productId":"13860428","title":"The Big Lebowski (Blu-ray)","current_price":{"value": 50,"currency_code":"USD"}

2. HTTP PUT request at the same path (/products/{id}), containing a JSON request body and updates the product’s price in the data store. 
Request: PUT http://localhost:8080/products/13860428
Request Body : {"productId":"54456119","current_price":{"value": 760.50,"currency_code":"USD"}

Response : Product Proce update for 54456119 : SUCCESS










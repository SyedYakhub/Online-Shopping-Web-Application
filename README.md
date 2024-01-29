# Online-Shopping-Web-Application
Developed an Online Shopping Application using Java and Spring Boot following the microservices architecture. 

Key Contributions & Technologies Used:

➢ Developed Product, Order, and Inventory microservices each comprising of Service, Controller,       Model, and Repository classes,

➢ Leveraged MongoDB as the database for the Product Service and MySQL for the Order and Inventory Services

➢ Enabled synchronous communication between the Order Service and Inventory Service using WebClient.

➢ Incorporated Resilience4J to build a circuit breaker mechanism, enhancing the system’s fault tolerance by triggering when any microservice is down.

➢ Configured an API Gateway to route user requests to different services, improving the system’s modularity and ease of use.

➢ Secured the services using an authorization server, KeyCloak, to ensure data privacy and security.

➢ Configured Eureka from Spring Cloud as a discovery server, facilitating efficient service-to-service       communication 

➢ Designed and implemented RESTful APIs using SpringBoot for the backend of the application

➢ Tested REST APIs using Postman, utilizing tokens for authentication.

<img width="931" alt="Architecture Diagram" src="https://github.com/SyedYakhub/Online-Shopping-Web-Application/assets/87276324/7128db9f-8ed5-4711-8054-3d3d98db2d3f">


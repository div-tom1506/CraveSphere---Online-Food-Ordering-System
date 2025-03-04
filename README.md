CraveSphere - Online Food Ordering System

CraveSphere is a microservices-based online food ordering system built using Spring Boot and MySQL. It allows users to register, browse restaurants, place orders, and make payments seamlessly.

🚀 Features

User Management: Register/Login with JWT authentication.

Restaurant Management: Add, update, and view restaurants.

Order Management: Users can place, track, and cancel orders.

Payment Integration: Demo payment processing using Stripe/Razorpay.

Microservices Architecture: Services communicate via RestTemplate.

Service Discovery & Load Balancing: Eureka Server.

API Gateway: Centralized API management using Spring Cloud Gateway.

🏗️ Tech Stack

Backend: Spring Boot, Spring Cloud, Spring Security, JWT Authentication

Database: MySQL

Communication: RestTemplate (Inter-Service Communication)

Payment Gateway: Stripe/Razorpay (Demo)

Service Discovery: Netflix Eureka

API Gateway: Spring Cloud Gateway

📂 Microservices Structure

1️⃣ User Service (8081)

Handles user registration & authentication.

Uses JWT for secure access.

Exposes APIs:

POST /api/users/register → Register a new user

POST /api/users/login → Login & get JWT Token

GET /api/users/{id} → Fetch user details

2️⃣ Restaurant Service (8082)

Manages restaurants (add, update, delete, view).

Exposes APIs:

POST /api/restaurants → Add a restaurant

GET /api/restaurants → List all restaurants

GET /api/restaurants/{id} → Get restaurant by ID

3️⃣ Order Service (8083)

Users can place and track orders.

Fetches user & restaurant details using RestTemplate.

Exposes APIs:

POST /api/orders → Place a new order

GET /api/orders/user/{userId} → Get orders by user

GET /api/orders/{orderId} → Fetch a specific order

4️⃣ Payment Service (8084)

Handles demo payments with Stripe/Razorpay.

Exposes APIs:

POST /api/payments → Process a payment

5️⃣ Eureka Server (8761)

Service discovery for all microservices.

Ensures dynamic scaling and load balancing.

Run Eureka Server before starting microservices.

6️⃣ API Gateway (9090)

Centralized routing for microservices.

Handles authentication, logging, and request forwarding.

Routes APIs dynamically.

🛠️ Setup Instructions

🔹 Prerequisites

Install Java 17+, Maven, and MySQL.

🔹 Backend Setup

Clone the repository:

git clone https://github.com/your-repo/cravesphere.git
cd cravesphere

Configure MySQL in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/cravesphere_db
spring.datasource.username=root
spring.datasource.password=yourpassword

Start Eureka Server before microservices:

cd eureka-server
mvn spring-boot:run

Start API Gateway:

cd api-gateway
mvn spring-boot:run

Build & Run microservices:

mvn clean install
mvn spring-boot:run

📌 API Documentation

Base URL: http://localhost:9090/api

Example: Fetch orders by user → GET http://localhost:9090/api/orders/user/1


📝 License

This project is for demo purposes only and is licensed under MIT License.

💡 Contributing

Contributions are welcome! Open an issue or submit a pull request.

✨ Authors

Your Name - Developer

🚀 Happy Coding with CraveSphere! 🍕


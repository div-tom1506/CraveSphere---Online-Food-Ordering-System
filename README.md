# CraveSphere - Online Food Ordering System

CraveSphere is a microservices-based online food ordering system built using Spring Boot and MySQL. It allows users to register, browse restaurants, place orders, and make payments seamlessly.

## Features

**User Management:** Register/Login with JWT authentication.

**Restaurant Management:** Add, update, and view restaurants.

**Order Management:** Users can place, track, and cancel orders.

**Payment Integration:** Demo Razorpay payment processing.

**Microservices Architecture:** Services communicate via RestTemplate.

**Service Discovery & Load Balancing:** Eureka Server.

**API Gateway:** Centralized API management using Spring Cloud Gateway.

## 🏗️ Tech Stack

**Backend:** Spring Boot, Spring Cloud, Spring Security, JWT Authentication

**Database:** MySQL

**Communication:** RestTemplate (Inter-Service Communication)

**Payment Gateway:** Razorpay (Demo)

**Service Discovery:** Netflix Eureka

**API Gateway:** Spring Cloud Gateway

## 📂 Microservices Structure

### 1️⃣ User Service (9001)

Handles user registration & authentication.

Uses JWT for secure access.

**Exposes APIs:**

`POST /api/users/register → Register a new user`

`POST /api/users/login → Login & get JWT Token`

`GET /api/users/{userId} → Fetch user details by userId`

`GET /api/users/email/{email} → Fetch user details by email`

`DELETE /api/users/{userId} → Remove user by userId`

### 2️⃣ Restaurant Service (9002)

Manages restaurants (add, update, delete, view).

**Exposes APIs:**

`POST /api/restaurants/add → Add a restaurant`

`GET /api/restaurants → List all restaurants`

`GET /api/restaurants/{id} → Get restaurant by ID`

`GET /api/restaurants/{name} → Get restaurant by name`

`PUT /api/restaurants/{id} → Update restaurant details by ID`

`DELETE /api/restaurants/{id} → Remove restaurant by ID`

### 3️⃣ Order Service (9003)

Users can place and track orders.

Fetches user & restaurant details using RestTemplate.

**Exposes APIs:**

`POST /api/orders → Place a new order`

`GET /api/orders/user/{userId} → Get orders by user`

`GET /api/orders/{orderId} → Fetch a specific order`

`PUT /api/orders/{orderId}/status → Update the status of order`

### 4️⃣ Payment Service (9004)

Handles demo payments with Razorpay.

**Exposes APIs:**

`POST /api/payments → Process a payment`

`GET /api/payments/{orderId} → Fetch a payment details using orderId`

`GET /api/payments/{transactionId} → Fetch a payment details using transactionId`

### 5️⃣ Eureka Server (8761)

Service discovery for all microservices.

Ensures dynamic scaling and load balancing.

Run Eureka Server before starting microservices.

### 6️⃣ API Gateway (9090)

Centralized routing for microservices.

Handles authentication, logging, and request forwarding.

Routes APIs dynamically.

## Setup Instructions

**🔹 Prerequisites**

Install Java 17+, Maven, and MySQL.

**🔹 Backend Setup**

**Clone the repository:**

    git clone https://github.com/div-tom1506/CraveSphere---Online-Food-Ordering-System.git
    cd CraveSphere---Online-Food-Ordering-System

**Configure MySQL in application.properties:**

    spring.datasource.url=jdbc:mysql://localhost:3306/cravesphere_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword

**Start Eureka Server before microservices:**

    cd eureka-server
    mvn spring-boot:run

**Start API Gateway:**

    cd api-gateway
    mvn spring-boot:run

**Build & Run microservices:**

    mvn clean install
    mvn spring-boot:run

## License

This project is for demo purposes only and is licensed under MIT License.

## Contributing

Contributions are welcome! Open an issue or submit a pull request.



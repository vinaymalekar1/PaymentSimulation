# Payment Simulation

This repository contains the backend implementation of a Payment Simulation System developed using Java and Spring Boot.

The backend is responsible for:

- User registration and authentication  
- Wallet creation and balance management  
- Money transfer between users  
- Transaction history tracking  
- JWT-based authentication and security  

It exposes REST APIs that can be consumed by any frontend application.

## How the Backend Works

- Built using Spring Boot with layered architecture (Controller, Service, Repository)  
- Users can register and automatically get a wallet with default balance  
- Users can login and receive a JWT token for authentication  
- All secured APIs require Authorization header with Bearer token  
- Users can transfer money with balance validation  
- Transactions are stored and retrieved as history  
- Uses Spring Data JPA for database interaction  

## How to Run Backend on Local Machine

1. Download or clone this repository  
2. Open IntelliJ IDEA or VS Code  
3. Open the project folder  
4. Ensure Maven dependencies are downloaded  
5. Configure database details in application.properties  
6. Create database in PostgreSQL:
7. Open the main class (PaymentApplication.java)  
8. Click the Run button  

Backend will start on:  
http://localhost:8080  

## Backend API Usage

### Auth APIs
- POST /api/auth/register – User registration  
- POST /api/auth/login – Login and get JWT  

### Wallet APIs
 GET /api/wallet/balance – Get wallet balance  

### Transaction APIs
- POST /api/transaction/transfer – Transfer money  
- GET /api/transaction/history – Get transaction history  

## Backend Requirements

- Java JDK 8 or above  
- IntelliJ IDEA or VS Code  
- Maven  
- PostgreSQL Database  

## Author
Vinay Malekar  

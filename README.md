# Promotion Management System - Backend

## Description
A Spring Boot backend application providing REST APIs to manage promotions, users, and roles. Supports CRUD operations, secure login, and admin/user role management.

## Features
- User authentication and role-based authorization
- Manage promotions (create, read, update, delete)
- Admin and user role management
- RESTful APIs for frontend consumption
- Input validation and error handling

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL database

### Setup
1. Clone the repository.
   ```bash
   git clone <backend-repo-url>

2. Navigate into the project folder.
   
   cd promotion-management-system-backend

3. Configure application.properties with your database credentials.
   
   spring.datasource.url=jdbc:mysql://localhost:3306/promotion_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword

   
4. Build and run the application.

### API Endpoints

UserController (/users/login) – Public

| Method | Endpoint          | Description                        |
| ------ | ----------------- | ---------------------------------- |
| `POST` | `/users/login`    | User login                         |


AdminController (/admins) – Admin only

| Method   | Endpoint       | Description         |
| -------- | -------------- | ------------------- |
| `POST`   | `/admins`      | Create a new user   |
| `GET`    | `/admins`      | Get all users       |
| `GET`    | `/admins/{id}` | Get a user by ID    |
| `PUT`    | `/admins/{id}` | Update a user by ID |
| `DELETE` | `/admins/{id}` | Delete a user by ID |


PromotionController (/promotions) – Admin & User

| Method   | Endpoint           | Description                                    |
| -------- | ------------------ | ---------------------------------------------- |
| `POST`   | `/promotions`      | Create a new promotion (multipart/form-data)   |
| `GET`    | `/promotions`      | Get all promotions                             |
| `GET`    | `/promotions/{id}` | Get a promotion by ID                          |
| `PUT`    | `/promotions/{id}` | Update a promotion by ID (multipart/form-data) |
| `DELETE` | `/promotions/{id}` | Delete a promotion by ID                       |




   

# 🚀 DevPulse Backend (Spring Boot Project)

## 📌 Project Overview

DevPulse is a backend application built using Spring Boot that enables users to share ideas, implement solutions, and vote on implementations.
The system includes authentication, role-based access, and a structured API design.

This project demonstrates real-world backend development concepts like authentication, REST APIs, and layered architecture.

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* MySQL Database
* Maven
* REST APIs
* Postman (for testing)

---

## 🔥 Features

* 🔐 User Authentication (JWT-based login/register)
* 👤 Role-based Authorization (Admin/User)
* 💡 Idea Management (Create, View, List ideas)
* 🛠 Implementation Management
* 🗳 Voting System (Toggle vote functionality)
* ⚠️ Global Exception Handling
* 📦 Layered Architecture (Controller → Service → Repository)

---

## 🏗 Project Structure

```
src/main/java/com/example
│
├── auth            # Authentication (Login/Register)
├── user            # User management
├── idea            # Idea module
├── implementation  # Implementation module
├── voting          # Voting system
├── securityconfig  # JWT + Security config
├── exception       # Global exception handling
```

---


## 🔑 Authentication Flow

1. User registers
2. User logs in → receives JWT token
3. Token is sent in headers for protected APIs
4. Backend validates token using JWT filter

---

## 📡 API Endpoints (Sample)
## 📡 API Endpoints

### 🌐 Public APIs (No authentication required)
Anyone can access these endpoints:

POST   /api/users                  → Register user  
POST   /api/auth/login             → Login  
GET    /api/ideas                  → Get all ideas  
GET    /api/ideas/trending         → Get trending ideas  
GET    /api/ideas/latest           → Get latest ideas  
GET    /api/implementations/idea/{id} → Get implementations by idea  
GET    /api/implementations/trending  → Get trending implementations  

---

### 👤 User APIs (Authentication required)
Accessible only by logged-in users:

POST   /api/ideas                 → Create idea  
POST   /api/implementations       → Submit implementation  
POST   /api/votes                 → Toggle vote  
PUT    /api/ideas/{id}            → Update own idea  
PUT    /api/implementations/{id}  → Update own implementation  
DELETE /api/ideas/{id}            → Delete own idea  
DELETE /api/implementations/{id}  → Delete own implementation  

---

### 🛠️ Admin APIs (Admin only)
Accessible only by admin users:

DELETE /api/users/{id}            → Delete any user  
GET    /api/users                 → Get all users  
GET    /api/users/{id}            → Get user by ID  
DELETE /api/ideas/{id}            → Delete any idea  
DELETE /api/implementations/{id}  → Delete any implementation  

## ▶️ How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/kondaharshini985-ops/devpulse_Project.git
```

### 2️⃣ Navigate to project

```bash
cd devpulse_Project
```

### 3️⃣ Run using Maven

```bash
mvn spring-boot:run
```

OR

```bash
./mvnw spring-boot:run
```

---

## 🗄 Database Configuration

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## 🧪 Testing

Use Postman to test APIs:

Add JWT token in header:

```
🔐 Authentication: JWT Token (Bearer Token in Authorization header)
```
### 🔑 Example Request (Login)

POST /api/auth/login

Request Body:
```json
{
  "email": "abc@gmail.com",
  "password": "password123"
}
```
---

## 🚀 Future Improvements

* Add frontend (React)
* API documentation using Swagger
* Pagination & filtering
* Docker deployment
* Unit & integration testing

---

## 🎯 Learning Outcomes

* Implemented JWT Authentication
* Built secure REST APIs
* Used layered architecture design
* Handled exceptions globally
* Integrated database with Spring Boot

---

## 👩‍💻 Author

Harshini Konda

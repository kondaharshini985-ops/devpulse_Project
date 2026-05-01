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

### 🔐 Auth

* POST `/auth/register`
* POST `/auth/login`

### 💡 Ideas

* POST `/ideas`
* GET `/ideas`
* GET `/ideas/{id}`

### 🛠 Implementation

* POST `/implementation`
* GET `/implementation/{id}`

### 🗳 Voting

* POST `/vote/toggle`

---

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
Authorization: Bearer <token>
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

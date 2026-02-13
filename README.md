# 🚀 Student Journey Sharing App
*A platform where students create accounts, share their professional journey, explore others' journeys, and grow together.*

---

## 📘 Overview
The **Student Journey Sharing App is a fronted + backend platform designed for students to share professional paths, achievements, and experiences.

This project includes:

- 📱 **Android Mobile App** (Java + Retrofit + XML UI)
- 🖥️ **Spring Boot Backend** (REST APIs)
- 🛢️ **MySQL Database + JPA**
- 🔐 **JWT Authentication + Spring Security**

---

## 🛠️ Tech Stack

### 📱 Frontend (Android App)
- Android Studio  
- Java  
- Retrofit  
- XML UI  

### 🖥️ Backend (Spring Boot)
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- Spring Security  
- JWT  
- REST API  

### 🛢️ Database
- MySQL

---

## ✨ Features

### 👤 User Module
- User Registration  
- User Login  
- JWT-based Authentication  
- Profile Management  

### 📝 Journey Module
- Create and share professional journey  
- View journeys from other students  
- Read stories and experiences  
- Search/filter journeys *(optional)* in future add

### 🔐 Security
- JWT token validation  
- Secure password hashing  
- Protected API endpoints  

---

## 📱 Android App Functionality
- Register and login  
- Post journey details  
- View journeys list  
- Retrofit networking  
- XML responsive UI  
- Error + loading states  

---

## 🖥️ Backend Workflow (Spring Boot)
- Handles REST API  
- JWT authentication  
- Stores data in MySQL  
- JPA for DB operations  
- JSON response output  

---

## 📁 Project Structure

### Backend
src/
├── controller/
├── service/
├── repository/
├── entity/
├── security/
├── dto/
└── config/
### Android App
app/
├── java/
│ ├── activities/
│ ├── adapters/
│ ├── models/
│ ├── network/
│ └── utils/
├── res/
│ ├── layout/
│ ├── drawable/
│ └── values/
└── AndroidManifest.xml


---

## 🧪 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login + JWT |

### 📝 Journey
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/journey` | Create new journey |
| GET  | `/api/journey` | Get all journeys |
| GET  | `/api/journey/{id}` | Get single journey |

---
Update `application.properties` with your MySQL username & password.

### Android App
1. Open Android Studio  
2. Import project  
3. Update Retrofit base URL  
4. Run on emulator or device  

---

## 🔐 JWT Authentication Flow
1. User logs in  
2. Backend validates credentials  
3. Backend generates JWT  
4. Android stores JWT in SharedPreferences  
5. Client sends token in headers  
6. Backend validates token on each request
_ _ 
 <p align="center">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/ac6be2979cba41d23fc7ca187dd7cc94408421b6/s%20sign%20up.jpeg" width="200">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/ef0c43866b40289ee798b03ceba2088744af7cce/slogin.jpeg" width="200">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/aa6b93e011b673f5a6b386d5a20ab94c67fbcb27/spost.jpeg" width="200">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/aa6b93e011b673f5a6b386d5a20ab94c67fbcb27/sallpost.jpeg" width="200">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/aa6b93e011b673f5a6b386d5a20ab94c67fbcb27/spost.jpeg" width="200">
  <img src="https://github.com/subhashkrkushwaha/ShareJourney/blob/aa6b93e011b673f5a6b386d5a20ab94c67fbcb27/ssetting.jpeg" width="200">
</p>

---

## 🙌 Contributions
Contributions, issues, and feature requests are welcome!

---

## 📄 License
This project is licensed under the **MIT License**.

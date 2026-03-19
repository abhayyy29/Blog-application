# 🚀 Blog Application - Backend

This is the backend of a Blog Application built using Spring Boot.
It provides REST APIs for authentication, managing posts, categories, comments, and handling image uploads.

This project helped me understand how real backend systems are built, including security, database handling, and API design.

---

## 🌟 Features

- JWT-based authentication and authorization
- User registration and login
- Create, update, delete, and view blog posts
- Category management
- Comment system
- Image upload (AWS S3 integration)
- Pagination and sorting

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- AWS S3
- Maven

---

## ⚙️ How to Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/your-username/blog-backend.git
cd blog-backend
```

### 2. Create database

```sql
CREATE DATABASE blog_app;
```

### 3. Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Config
jwt.secret=your_secret_key
jwt.expiration=604800000

# AWS S3
aws.s3.bucket=your-bucket-name
aws.region=eu-north-1
```

### 4. Run the project

```bash
mvn clean install
mvn spring-boot:run
```

App will run on:
http://localhost:8080

---

## 🔐 Authentication Flow

1. Register user
2. Login → get JWT token
3. Use token in headers:

```
Authorization: Bearer <token>
```

---

## 📌 API Overview

### Auth APIs
- POST /api/auth/register
- POST /api/auth/login

### Post APIs
- POST /api/user/{userId}/category/{categoryId}/posts
- GET /api/posts
- GET /api/posts/{postId}
- PUT /api/posts/{postId}
- DELETE /api/posts/{postId}

### Comment APIs
- POST /api/post/{postId}/comments
- GET /api/post/{postId}/comments

### Category APIs
- POST /api/categories
- GET /api/categories
- DELETE /api/categories/{categoryId}

---

## 🧪 Testing

You can test APIs using Postman.

---

## 🚀 Deployment

- AWS Elastic Beanstalk
- AWS RDS (MySQL)
- AWS S3

---

## 📌 Future Improvements

- Like Posts
- Search functionality
- Admin dashboard

---

## 👨‍💻 Author

Abhay Singh

---

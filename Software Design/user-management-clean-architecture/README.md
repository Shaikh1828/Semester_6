
# User Management System (Clean Architecture)

This project implements a simple **User Management and Role Assignment System** using **Spring Boot** and follows the **Clean Architecture** principles.

---

## ✅ Features

- Add user with name and email.
- Assign roles (e.g., STUDENT, ADMIN) to users.
- Retrieve user details with assigned roles.
- In-memory H2 database support.
- Validation and error handling.
- Clean separation of concerns (Domain, Application, Infrastructure).

---

## 🏗️ Project Structure (Clean Architecture)

```
src/
├── domain/                   # Business logic (NO Spring/JPA here)
│   ├── Role.java
│   └── User.java

├── application/             # Use cases / Service layer
│   ├── UserService.java
│   └── RoleService.java

├── application/interfaces/  # Repository interfaces (Ports)
│   ├── UserRepository.java
│   └── RoleRepository.java

├── infrastructure/
│   ├── controller/          # REST Controllers
│   │   ├── UserController.java
│   │   └── RoleController.java
│   ├── persistence/         # Database layer (Adapters)
│   │   ├── UserJpaEntity.java
│   │   ├── RoleJpaEntity.java
│   │   ├── UserJpaRepository.java
│   │   ├── RoleJpaRepository.java
│   │   ├── UserRepositoryImpl.java
│   │   └── RoleRepositoryImpl.java

├── config/
│   └── BeanConfig.java

└── resources/
    ├── application.properties
    └── data.sql (optional for seeding data)
```

---

## ▶️ Run the App

```
mvn spring-boot:run
```

Then visit: [http://localhost:8081](http://localhost:8080)

---

## 🧪 Sample API Calls (Using Postman or cURL)

### 1. Create a User

```
POST /users
Content-Type: application/json

{
  "name": "Shaikh",
  "email": "hello@gmail.com"
}
```

### 2. Create a Role

```
POST /roles
Content-Type: application/json

{
  "roleName": "STUDENT"
}
```

### 3. Assign Role to User

```
POST /users/{userId}/roles/{roleId}
```

### 4. Get User Info

```
GET /users/{userId}
```

---

## 🛠️ H2 Console

```
http://localhost:8081/h2-console

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: [leave blank]
```

---

## ✅ Validation

- Name and Email are required.
- Email format is checked.
- Proper 400 Bad Request is returned on invalid input.

---

## 🧪 Testing

Unit tests are under: `src/test/java/com/example/usermanagement/application`

Run with:
```
mvn test
```

---

## 📦 Dependencies

- Spring Boot Web
- Spring Data JPA
- H2 Database
- Jakarta Validation
- JUnit 4 + Mockito

---

## Author

Prepared as a Software Design course assignment following Clean Architecture principles.

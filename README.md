# Journal Application

A production-ready Spring Boot application that allows users to securely manage their daily journals. It provides user authentication, real-time weather integration, email notifications, and a RESTful API layer

---

## Features

###  Security
- JWT-based user authentication and authorization
- Role-based access control (Admin and User)
- Secure password storage and validation

###  Core Functionality
- CRUD operations for journal entries
- Personalized dashboard for users
- Search and filter entries by id/name

###  Integrations
- Email notification service for user actions
- Real-time weather data via external API
- Scheduled user activity checks

###  Developer Tools
- Swagger UI for API documentation
- Modular architecture (Config, Controller, Service, Repository)
- Maven build support


---

## Technology Stack

| Component | Technology            |
| Language  | Java 17+              |
| Framework | Spring Boot           |
| Database  | MongoDB               |                    
| Security  | Spring Security + JWT |
| Scheduler | Spring Task Scheduler |
| API Docs  | Swagger (springdoc)   |
| Build     | Maven                 |

---

## Project Structure

```
src/main/java/com/kalyani/journalApplication
 ├── Config
 │    ├── AppConfig.java
 │    ├── SpringSecurity.java
 │    ├── SpringSecurityProd.java
 │    └── SwaggerConfig.java
 ├── Controller
 │    ├── AdminController.java
 │    ├── JournalEntryController.java
 │    ├── PublicController.java
 │    └── UserController.java
 ├── Model
 ├── Repository
 ├── Scheduler
 │    └── UserScheduler.java
 ├── Service
 │    ├── EmailService.java
 │    ├── JournalEntryService.java
 │    ├── CustomUserDetailsServiceImpl.java
 │    ├── UserService.java
 │    └── WeatherService.java
 ├── Utils
 │    └── JwtUtil.java
 └── JournalApplication.java


---

## Prerequisites

* Java 17 or higher
* MongoDB installed locally or cloud URI
* Maven

---

## Configuration

### MongoDB

```
spring.data.mongodb.uri=mongodb://localhost:27017/journal_db
```

### JWT Secret

```
jwt.secret=<your-secret-key>
jwt.expiration=86400000
```

### Email SMTP (if used)

```
spring.mail.host=smtp.gmail.com
spring.mail.username=<email>
spring.mail.password=<app-password>
```

Set these in `application.yml` or environment variables.

---

## Installation & Setup

   Clone the repository:
   ```bash
   git clone https://github.com/enupala/JournalApp.git
   cd JournalApp


mvn clean install
mvn spring-boot:run


Application Server runs at:

```
http://localhost:8070
```

---

## API Endpoints

### Authentication

| Method | Endpoint           | Description                    |
| ------ | ------------------ | ------------------------------ |
| POST   | `/public/register` | User registration              |
| POST   | `/public/login`    | User login (returns JWT token) |

### Journal Management

| Method | Endpoint               | Description                            |
| ------ | ---------------------- | -------------------------------------- |
| GET    | `/journal/all`         | Get journal entries for logged-in user |
| POST   | `/journal/add`         | Add a new entry                        |
| PUT    | `/journal/update/{id}` | Update entry                           |
| DELETE | `/journal/delete/{id}` | Delete entry                           |

### Admin

| Method | Endpoint           | Description               |
| ------ | ------------------ | ------------------------- |
| GET    | `/admin/all-users` | View all registered users |

Authorization Header Required:

```
Authorization: Bearer <token>
```

---

## Swagger Documentation

Open in browser:

```
http://localhost:8070/swagger-ui.html
```

---

## Scheduled Tasks

* `UserScheduler` runs periodic checks and automated actions.

---

## Weather API Integration

Weather data is fetched from an external API and returned in response format defined in `WeatherResponse.java`.


## Tests

JUnit and Spring Boot test cases are located in:

```
src/test/java/com/kalyani/journalApplication
```
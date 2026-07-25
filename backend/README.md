# ✈️ Airline Reservation System

A full-stack **Airline Reservation System** developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**. The application provides a complete flight booking experience with passenger management, reservation handling, baggage charge calculation, and RESTful APIs.

## 🚀 Features

### ✈️ Flight Management
- View all available flights
- Search flights by source and destination
- Check flight details and seat availability

### 🎫 Reservation Management
- Book flight reservations
- View reservation details
- Cancel reservations
- Generate unique reservation codes

### 👤 Passenger Management
- Store passenger information
- Manage passenger details securely

### 🧳 Smart Baggage Management
- Automatic baggage type detection
- Baggage weight validation
- Automatic baggage charge calculation
- Detailed pricing breakdown

---

## 💼 Baggage Pricing Policy

| Baggage Type | Weight Limit | Charge |
|--------------|-------------|--------|
| Carry-on | Up to 7 kg | Free |
| Checked | Up to 23 kg | ₹400 |
| Overweight | Above 23 kg | ₹400 + ₹300 per extra kg |

---

# 🛠 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- REST API

### Database
- MySQL

### Build Tool
- Maven

### Validation
- Jakarta Bean Validation

### Testing
- Postman

---

# 🏗️ Project Architecture

The project follows a layered architecture:

```
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
MySQL Database
```

### Design Pattern

- MVC Architecture
- Layered Architecture
- Repository Pattern
- DTO Pattern

---

# 📂 Project Structure

```
src
│
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
└── resources
```

---

# 🌐 REST API Endpoints

## Flight APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/flights` | Get all flights |
| GET | `/api/flights/{id}` | Get flight by ID |
| GET | `/api/flights/search` | Search flights |

---

## Reservation APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/reservations` | Create reservation |
| GET | `/api/reservations/{id}` | Get reservation |
| GET | `/api/reservations/code/{code}` | Search by reservation code |
| DELETE | `/api/reservations/{id}` | Cancel reservation |

---

## Baggage APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/baggage/calculate` | Calculate baggage charges |

---

# 🗄️ Database Tables

- Flights
- Passengers
- Reservations
- Baggage

---

# ⚙️ Installation & Setup

## Clone Repository

```bash
git clone https://github.com/Abhishek-Thakur14/Airline-Reservation-System.git
```

Navigate to the project folder

```bash
cd Airline-Reservation-System
```

Update database credentials in:

```
src/main/resources/application.properties
```

Run the project

```bash
mvn spring-boot:run
```

The application will start at

```
http://localhost:8080
```

---

# 📷 API Testing

The REST APIs were tested using **Postman** to verify:

- Flight APIs
- Reservation APIs
- Passenger APIs
- Baggage Calculation APIs

---

# ✨ Key Highlights

- Java OOP Concepts
- Spring Boot Framework
- RESTful API Development
- CRUD Operations
- Spring Data JPA & Hibernate
- MySQL Database Integration
- Exception Handling
- Input Validation
- Layered Architecture
- Maven Project Structure

---

# 🚀 Future Improvements

- Online Payment Gateway
- Seat Selection
- Flight Schedule Management
- Email Notifications
- SMS Notifications
- User Authentication & Authorization
- Admin Dashboard
- Responsive Web Interface
- Mobile Application

---

# 👨‍💻 Developed By

**Abhishek Kumar**

- 💼 Java Developer
- 🌐 Full Stack Developer
- ☕ Spring Boot Developer

GitHub: **https://github.com/Abhishek-Thakur14**

LinkedIn: **https://linkedin.com/in/abhishekkumar1414**

Email: **thecode.abhi@gmail.com**

---

## ⭐ If you found this project helpful, don't forget to star the repository!

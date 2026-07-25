# ✈️ Airline Management System

A full-stack Airline Management System developed using **Spring Boot**, **React.js**, and **MySQL**. The application provides a modern platform for managing flights, passengers, bookings, and airline operations with secure authentication and an intuitive user interface.

---

## 🚀 Features

### Admin
- Add, update, and delete flights
- Manage airline routes
- View all bookings
- Manage passenger information
- Dashboard with airline statistics

### User
- User Registration & Login
- Search available flights
- Book flight tickets
- View booking history
- Cancel bookings
- Profile management

---

## 🛠 Tech Stack

### Frontend
- React.js
- HTML5
- CSS3
- JavaScript
- Axios
- React Router

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- REST API
- Maven

### Database
- MySQL

### Tools
- IntelliJ IDEA
- VS Code
- Postman
- Git
- GitHub

---

## 📂 Project Structure

```
Airline-Management-System/
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── ...
│
└── README.md
```

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/your-username/Airline-Management-System.git
```

---

### Backend Setup

```bash
cd backend
```

Configure MySQL database in:

```
application.properties
```

Run:

```bash
mvn spring-boot:run
```

Backend will run on:

```
http://localhost:8080
```

---

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend will run on:

```
http://localhost:3000
```

---

## 🔗 REST API

Sample APIs:

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/auth/register | Register User |
| POST | /api/auth/login | Login |
| GET | /api/flights | Get All Flights |
| GET | /api/flights/{id} | Get Flight |
| POST | /api/bookings | Book Ticket |
| GET | /api/bookings | View Bookings |

---

## 📸 Screenshots

Add screenshots here after deployment.

Example:

- Login Page
- Dashboard
- Flight Search
- Booking Page
- Admin Panel

---

## 🔒 Security

- JWT Authentication
- Password Encryption
- Role-based Authorization
- Input Validation

---

## 🎯 Future Enhancements

- Online Payment Gateway
- Seat Selection
- Email Notifications
- Ticket PDF Generation
- Flight Tracking
- Mobile Responsive UI

---

## 👨‍💻 Author

**Abhishek Kumar**

GitHub: https://github.com/Abhishek-Thakur14

LinkedIn: www.linkedin.com/in/abhishek-thakur14

---

## ⭐ Support

If you like this project, please give it a ⭐ on GitHub.

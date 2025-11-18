
```md
# ✈️ Flight Ticket Booking System

A complete backend application that supports **flight search, ticket booking, seat selection, meal choice, PNR lookup, and booking cancellation** — built with clean layered architecture in Spring Boot.

It follows:
- DTO-based request/response
- Entity-based persistence
- Service-based business logic
- Repository-based DB operations

---

## 🧱 Database ER Diagram

> Includes Airline, Flight, User, Booking, Passenger relations

[View on Eraser![](https://app.eraser.io/workspace/yjyzLhjT3YkUowdBQo1N/preview?elements=xHWSCiz570INT23IoLfOQg&type=embed)](https://app.eraser.io/workspace/yjyzLhjT3YkUowdBQo1N?elements=xHWSCiz570INT23IoLfOQg)

**Relationships (Plain English):**

- Airline → Flight = **1 to Many**
- Flight → Booking = **1 to Many**
- Booking → Passenger = **1 to Many**
- User → Booking = **1 to Many**
- Seats are unique per flight

---

## 🧾 Key Features

✔ Flight search  
✔ Airline logo + pricing  
✔ PNR generation  
✔ Meal choice per passenger  
✔ Seat number validation  
✔ Prevent double seat booking  
✔ Booking history by email  
✔ Booking cancellation rules (> 24 hrs left)  

---

## 🔧 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Validation | Jakarta Validation |
| Build Tool | Maven |
| Testing | JUnit 5 + Mockito |
| Code Quality | SonarQube |
| Dev Tools | Lombok (optional) |

---

## 📁 Project Structure

```

src/
├── main/
│   ├── java/com/flightapp/
│   │   ├── controller/     → REST endpoints
│   │   ├── dto/            → Request & Response objects
│   │   │    ├── request/
│   │   │    └── response/
│   │   ├── entity/         → JPA entities
│   │   ├── repository/     → Interfaces for DB
│   │   ├── service/        → Service interfaces
│   │   └── service/impl/   → Business logic impl
│   └── resources/
│       ├── application.properties
│       └── schema.sql (optional)
└── test/
└── java/com/flightapp/
└── service/impl/   → Unit tests

```

---

## 🧠 Data Flow

### 1️⃣ User hits an endpoint
Example:
```

POST /api/v1.0/flight/booking/{flightId}

````

### 2️⃣ JSON mapped to Request DTO

**BookFlightRequest**
```json
{
  "userName": "Soham",
  "userEmail": "soham@example.com",
  "numberOfSeats": 2,
  "passengers": [
    { "name": "A", "seatNumber": "12A", "mealType": "VEG" }
  ]
}
````

### 3️⃣ Service Layer executes logic

* Check seat availability
* Generate PNR
* Update available seats
* Save booking
* Save passengers

### 4️⃣ Repositories persist data

No raw SQL — Spring Data JPA automates it.

### 5️⃣ Response DTO is returned

**TicketResponse**

```json
{
  "pnr": "AI2512QW8X",
  "status": "BOOKED",
  "userName": "Soham",
  "numberOfSeats": 2
}
```

---

## 🧪 Test Coverage

Unit tests verify:

* Seat not available
* Successful booking
* PNR lookup
* Invalid PNR
* Cancellation < 24 hrs → fail
* No booking history

Run tests:

```sh
mvn test
```

---

## 📊 SonarQube Report

* Quality Gate: **Passed**
* Bugs: **0 (A rating)**
* Code Smells: minimal (DTO boilerplate)
* Coverage: extendable (via JaCoCo)
* Duplicates: reduced using Lombok

---

## 🛠 Run Locally

### 1. Clone repo

```sh
git clone <repo-url>
cd flight-ticket-booking
```

### 2. Create Database

```sql
CREATE DATABASE flightdb;
```

### 3. Update config

`src/main/resources/application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/flightdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 4. Start server

```sh
mvn spring-boot:run
```

API available at:

```
http://localhost:8086
```

---

## 📌 Final Notes

This project includes:

* **Real-world booking logic**
* **Business rules**
* **DB constraints**
* **Clean layering**
* **DTO separation**
* **Unit tests**
* **Static code analysis**

Perfect for:

* Interviews
* GitHub portfolio
* Backend developer role
* Spring Boot learning

---


```
```

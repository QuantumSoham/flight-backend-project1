# FLIGHT BOOKING SYSTEM 

## DATABASE DESIGN
<img width="3064" height="2330" alt="image" src="https://github.com/user-attachments/assets/239fb517-1d70-4830-8751-0d8cc1ef9bb1" />


## Architecture Overview



This project follows a clean layered Spring Boot architecture:

* **Controller Layer** – Exposes REST APIs, accepts JSON, returns JSON.
* **DTO Layer** – Request/Response objects, keeps API separate from internal models.
* **Service Layer** – Contains business logic (seat validation, booking, cancellation, PNR generation).
* **Entity Layer** – JPA models for Airline, Flight, Booking, Passenger, UserAccount.
* **Repository Layer** – Spring Data JPA interfaces to fetch and save data.
* **Utility Layer** – Helpers like `PnrGenerator` that encapsulate shared logic.

Data flow:

> Client → Controller → Request DTO → Service → Entities → Repository → Database
> Database → Entities → Service → Response DTO → Controller → Client

---

## Booking API Flow (How it works)

1. User sends a booking request with flight ID + passenger details.
2. Controller converts JSON to DTO and calls Service.
3. Service:

   * Validates seats and availability.
   * Fetches flight & (optional) user.
   * Creates a Booking record.
   * Generates a unique PNR.
   * Saves passengers linked to the booking.
   * Updates available seats.
4. Service returns a TicketResponse containing PNR, flight info, and passenger list.

---

## PNR Generation — Why It’s Special

The PNR is not a simple random string. It combines:

* **Flight prefix** (e.g., first 3 chars of flight number)
* **Timestamp** (booking moment in MMddHHmm)
* **Cryptographically secure random characters**
* **A short SHA-256 hash** based on the booked seat numbers

### Why this design?

* **Very low collision probability**
* **Not predictable** or guessable
* **Tied to actual seats booked**
* **Readable but still secure**
* **Fully encapsulated in a utility class**



## MySQL Database Design

### 1. Table: `patients`

| Column Name | Data Type    | Constraints                                          | Notes                        |
|-------------|--------------|------------------------------------------------------|------------------------------|
| id          | INT          | PRIMARY KEY, AUTO_INCREMENT                          | Unique patient               |
| first_name  | VARCHAR(50)  | NOT NULL                                             |                              |
| last_name   | VARCHAR(50)  | NOT NULL                                             |                              |
| dob         | DATE         | NOT NULL                                             | Date of birth                |
| gender      | VARCHAR(1)   | NOT NULL                                             | M - male, F - female         |
| email       | VARCHAR(100) | UNIQUE                                               | Validate via code            |
| phone       | VARCHAR(20)  |                                                      | Validate via code            |
| address     | VARCHAR(255) |                                                      |                              |
| created_at  | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP                  |                              |
| updated_at  | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE        |                              |

<br>

### 2. Table: `doctors`

| Column Name | Data Type    | Constraints                                          | Notes                        |
|-------------|--------------|------------------------------------------------------|------------------------------|
| id          | INT          | PRIMARY KEY, AUTO_INCREMENT                          | Unique doctor                |
| first_name  | VARCHAR(50)  | NOT NULL                                             |                              |
| last_name   | VARCHAR(50)  | NOT NULL                                             |                              |
| specialty   | VARCHAR(100) |                                                      |                              |
| email       | VARCHAR(100) | UNIQUE                                               | Validate via code            |
| phone       | VARCHAR(20)  |                                                      | Validate via code            |
| created_at  | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP                  |                              |
| updated_at  | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE        |                              |

<br>

### 3. Table: `appointments`

| Column Name     | Data Type    | Constraints                                                                                  | Notes                                    |
|-----------------|--------------|----------------------------------------------------------------------------------------------|------------------------------------------|
| id              | INT          | PRIMARY KEY, AUTO_INCREMENT                                                                  | Unique appointment                       |
| patient_id      | INT          | NOT NULL, FOREIGN KEY REFERENCES patients(patient_id) ON DELETE CASCADE                      | Cascade delete if patient is deleted     |
| doctor_id       | INT          | NOT NULL, FOREIGN KEY REFERENCES doctors(doctor_id) ON DELETE RESTRICT                       | Restrict delete if appointments exist    |
| clinic_id       | INT          | NOT NULL, FOREIGN KEY REFERENCES clinic_locations(clinic_id)                                 |                                          |
| scheduled_at    | DATETIME     | NOT NULL                                                                                     |                                          |
| duration_min    | INT          | NOT NULL                                                                                     | Duration in minutes                      |
| reason          | VARCHAR(255) |                                                                                              |                                          |
| status          | VARCHAR(1)   | NOT NULL, DEFAULT 'scheduled'                                                                | S-scheduled, C-completed, X-canceled     |
| created_at      | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP                                                          |                                          |
| updated_at      | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE                                                |                                          |

**Constraint:**  
_Doctor should not have overlapping appointments. Enforce via application logic, or via a unique index on (`doctor_id`, `scheduled_at`) if time slots are discrete._
<br>

### 4. Table: `admins`

| Column Name   | Data Type    | Constraints                                          | Notes                        |
|---------------|--------------|------------------------------------------------------|------------------------------|
| id            | INT          | PRIMARY KEY, AUTO_INCREMENT                          | Unique admin                 |
| username      | VARCHAR(50)  | NOT NULL, UNIQUE                                     |                              |
| password_hash | VARCHAR(255) | NOT NULL                                             | Store hash, not plain        |
| email         | VARCHAR(100) | UNIQUE                                               | Validate via code            |
| created_at    | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP                  |                              |
| updated_at    | DATETIME     | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE        |                              |

<br>

### 5. Table: `clinic_locations`

| Column Name | Data Type    | Constraints                   | Notes            |
|-------------|--------------|-------------------------------|------------------|
| id          | INT          | PRIMARY KEY, AUTO_INCREMENT   | Unique location  |
| name        | VARCHAR(100) | NOT NULL                      |                  |
| address     | VARCHAR(255) | NOT NULL                      |                  |
| phone       | VARCHAR(20)  |                               | Validate via code|

<br>

### 6. Tables: `payments`

| Column Name    | Data Type    | Constraints                                                                     | Notes                  |
|----------------|--------------|---------------------------------------------------------------------------------|------------------------|
| id             | INT          | PRIMARY KEY, AUTO_INCREMENT                                                     | Unique payment         |
| appointment_id | INT          | NOT NULL, FOREIGN KEY REFERENCES appointments(appointment_id) ON DELETE CASCADE | Tie to appointment     |
| amount         | DECIMAL(10,2)| NOT NULL                                                                        |                        |
| status         | VARCHAR(1)   | NOT NULL, DEFAULT 'pending'                                                     | P-pending, C-completed |
| paid_at        | DATETIME     |                                                                                 |                        |

<br>

## Additional Notes

- **Email and Phone Validation:** Should be handled in application code.
- **ON DELETE CASCADE:** Appointments are deleted if a patient is deleted.
- **ON DELETE RESTRICT:** Prevents deleting doctors with existing appointments.
- **Overlapping Appointments:** Prevent via application logic or DB constraint if possible.
- **UNIQUE and NOT NULL:** Use for emails, usernames, and required fields.
- **AUTO_INCREMENT:** For all primary keys.

---

## MongoDB Collection Design

### Collection: prescriptions
```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
  }
}

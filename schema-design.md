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
<br>

## MongoDB Collection Design

### 1. Collection: `doctor_notes`

```json
{
  "_id": "ObjectId",
  "patient_id": { "type": "ObjectId", "ref": "patients", "required": true },
  "doctor_id": { "type": "ObjectId", "ref": "doctors", "required": true },
  "appointment_id": { "type": "ObjectId", "ref": "appointments" },
  "note": { "type": "String", "required": true }, // Free-form text
  "created_at": { "type": "Date", "default": "Date.now" },
  "updated_at": { "type": "Date" }
}
```
<br>

### 2. Collection: `patient_feedback`

```json
{
  "patient_feedback": {
    "_id": "ObjectId",
    "appointment_id": { "type": "ObjectId", "ref": "appointments", "required": true },
    "patient_id": { "type": "ObjectId", "ref": "patients", "required": true },
    "feedback_text": { "type": "String" }, 
    "rating": { "type": "Number", "min": 1, "max": 5 }, 
    "created_at": { "type": "Date", "default": "Date.now" }
  }
}
```
<br>

### 3. Collection: `prescriptions`

```json
{
  "_id": "ObjectId",
  "appointment_id": { "type": "ObjectId", "ref": "appointments", "required": true },
  "patient_id": { "type": "ObjectId", "ref": "patients", "required": true },
  "doctor_id": { "type": "ObjectId", "ref": "doctors", "required": true },
  "medications": [
    {
      "name": { "type": "String", "required": true },
      "dosage": { "type": "String" },
      "frequency": { "type": "String" },
      "duration": { "type": "String" },
      "instructions": { "type": "String" }
    }
  ],
  "notes": { "type": "String" },
  "issued_at": { "type": "Date", "default": "Date.now" }
}
```
<br>

### 4. Collections: `attachments`

```json
{
  "attachments": {
    "_id": "ObjectId",
    "related_type": { "type": "String", "enum": ["appointment", "doctor_note", "prescription"], "required": true },
    "related_id": { "type": "ObjectId", "required": true },
    "uploaded_by": { "type": "ObjectId", "ref": "users", "required": true },
    "filename": { "type": "String", "required": true },
    "file_url": { "type": "String", "required": true },
    "mime_type": { "type": "String" },
    "size": { "type": "Number" },
    "uploaded_at": { "type": "Date", "default": "Date.now" }
  }
}
```

### 5. Collection: `log_records`

```json
{
  "logs": {
    "_id": "ObjectId",
    "event_type": { "type": "String", "enum": ["check_in", "message"], "required": true },
    "patient_id": { "type": "ObjectId", "ref": "patients", "required": true },
    "doctor_id": { "type": "ObjectId", "ref": "doctors" },
    "appointment_id": { "type": "ObjectId", "ref": "appointments" },
    "message": { "type": "String" }, // For message events
    "timestamp": { "type": "Date", "default": "Date.now" },
    "metadata": { "type": "Object" } // Any extra info (location, device, etc.)
  }
}
```
<br>

**Notes:**
- ObjectId types are for MongoDB references; use population as needed.
- Attachments can reference multiple entity types via `related_type` and `related_id`.
- All collections include timestamps for audit and tracking.



# Admin User Stories

## 1. Log into the Portal to Manage the Platform

**As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely.**

### Acceptance Criteria
- Admins can log in with their registered username and password.
- Successful login redirects to the admin dashboard.
- Login failures display informative error messages.
- Admin session is secured to prevent unauthorized access.

**Priority:** High  
**Story Points:** 2

**Notes:**
- Consider adding two-factor authentication for added security.

---

## 2. Log Out of the Portal

**As an admin, I want to log out of the portal, so that I can protect system access.**

### Acceptance Criteria
- A visible logout option is available on all admin pages.
- Logging out ends the admin session and redirects to the login page.
- No admin data or privileged controls are accessible after logout.

**Priority:** High  
**Story Points:** 1

**Notes:**
- Ensure session tokens/cookies are invalidated on logout.

---

## 3. Add Doctors to the Portal

**As an admin, I want to add doctors to the portal, so that new doctors can be listed and start receiving appointments.**

### Acceptance Criteria
- Admin can access a form to add doctor details (name, specialization, contact, etc.).
- Upon successful submission, the doctor is added to the publicly viewable list.
- Validation ensures required fields are completed and duplicate entries are prevented.

**Priority:** High  
**Story Points:** 3

**Notes:**
- Consider notifications for doctors when they are added.

---

## 4. Delete Doctor's Profile

**As an admin, I want to delete a doctor's profile from the portal, so that outdated or incorrect profiles are removed from the system.**

### Acceptance Criteria
- Admin can select a doctor and confirm deletion.
- Deleting a doctor removes the profile from all patient-facing lists.
- The system ensures appointments linked to the deleted doctor are handled appropriately (e.g., notify patients).

**Priority:** High  
**Story Points:** 3

**Notes:**
- Consider a soft delete for audit purposes.

---

## 5. Run a Stored Procedure to Track Usage

**As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that I can track usage statistics.**

### Acceptance Criteria
- Admin has access to documentation or an interface to run the stored procedure.
- The stored procedure returns appointment counts per month.
- Results are presented in an easy-to-read format for reporting.

**Priority:** Medium  
**Story Points:** 2

**Notes:**
- Consider automating this reporting in the admin dashboard.

# Patient User Stories

## 1. View a List of Doctors Without Logging In

**As a patient, I want to view a list of doctors without logging in, so that I can explore options before registering.**

### Acceptance Criteria
- The system displays a publicly accessible list of doctors, including names, specializations, and available hours.
- The patient does not need to log in or register to view this list.
- Information is up to date and matches the data available to registered users.

**Priority:** High  
**Story Points:** 2

**Notes:**
- The list should be easy to search and filter (by specialization, location, etc.).

---

## 2. Sign Up Using Email and Password

**As a patient, I want to sign up using my email and password, so that I can book appointments.**

### Acceptance Criteria
- A registration page is available to patients.
- The registration form requires email and password.
- On successful registration, the patient can access the booking system.
- Proper validation and error messages are shown for invalid input.

**Priority:** High  
**Story Points:** 3

**Notes:**
- Consider email verification after registration.
- Passwords must be securely stored (hashed).

---

## 3. Log Into the Portal to Manage Bookings

**As a patient, I want to log into the portal to manage my bookings, so that I can access and modify my appointments securely.**

### Acceptance Criteria
- Patients can log in with their registered email and password.
- Successful login redirects to the patient dashboard.
- Login failures display informative error messages.

**Priority:** High  
**Story Points:** 2

**Notes:**
- Persistent login session should be secure.
- Consider adding "forgot password" support.

---

## 4. Log Out of the Portal

**As a patient, I want to log out of the portal to secure my account, so that my information is protected when I leave.**

### Acceptance Criteria
- A visible logout option is available from all authenticated pages.
- Logging out ends the user session and redirects to the public home page.
- No sensitive data is accessible after logout.

**Priority:** High  
**Story Points:** 1

**Notes:**
- Ensure session tokens/cookies are invalidated on logout.

---

## 5. Book an Hour-Long Appointment

**As a patient, I want to book an hour-long appointment with a doctor after logging in, so that I can consult with a doctor at my convenience.**

### Acceptance Criteria
- Patients can select a doctor and choose an available 1-hour slot.
- Double-booking for the same slot is prevented.
- Booking confirmation is sent (email or dashboard notification).

**Priority:** High  
**Story Points:** 5

**Notes:**
- Time zone considerations for appointments.
- Consider cancellation/rescheduling options.

---

## 6. View Upcoming Appointments

**As a patient, I want to view my upcoming appointments, so that I can prepare accordingly.**

### Acceptance Criteria
- Logged-in patients can access a list of all upcoming appointments.
- Each appointment displays doctor’s name, time, and location/medium (in-person or online).
- Patients are notified of any changes to their appointments.

**Priority:** High  
**Story Points:** 2

**Notes:**
- Integrate with calendar reminders if possible.



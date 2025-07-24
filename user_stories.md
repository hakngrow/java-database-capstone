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


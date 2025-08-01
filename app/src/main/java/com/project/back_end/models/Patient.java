package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Patient {

    // 1. 'id' field:
    //    - Type: private Long
    //    - Description:
    //      - Represents the unique identifier for each patient.
    //      - The @Id annotation marks it as the primary key.
    //      - The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 'name' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the patient's full name.
    //      - The @NotNull annotation ensures that the patient's name is required.
    //      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters.
    //      - Provides validation for correct input and user experience.
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100)
    private String name;

    // 3. 'email' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the patient's email address.
    //      - The @NotNull annotation ensures that an email address must be provided.
    //      - The @Email annotation validates that the email address follows a valid email format (e.g., patient@example.com).
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be a valid format")
    private String email;

    // 4. 'password' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the patient's password for login authentication.
    //      - The @NotNull annotation ensures that a password must be provided.
    //      - The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.
    @NotNull(message = "Password cannot be null")
    @Size(min = 6)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 5. 'phone' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the patient's phone number.
    //      - The @NotNull annotation ensures that a phone number must be provided.
    //      - The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.
    @NotNull(message = "Phone cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    // 6. 'address' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the patient's address.
    //      - The @NotNull annotation ensures that the address must be provided.
    //      - The @Size(max = 255) annotation ensures that the address does not exceed 255 characters in length, providing validation for the address input.
    //@NotNull(message = "Address cannot be null")
    @Size(max = 255)
    private String address;

    //@NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "\\d{10}", message = "Emergency contact must be 10 digits")
    private String emergencyContact;

    @Size(min=3, max = 100)
    private String insuranceProvider;

    // 7. Getters and Setters:
    //    - Standard getter and setter methods are provided for all fields: id, name, email, password, phone, and address.
    //    - These methods allow access and modification of the fields of the Patient class.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public Patient() {
    }

    public Patient(Long id, String name, String email, String password, String phone, String address,
                   LocalDate dateOfBirth, String emergencyContact, String insuranceProvider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.insuranceProvider = insuranceProvider;
    }
}

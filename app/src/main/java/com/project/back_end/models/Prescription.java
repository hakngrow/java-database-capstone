package com.project.back_end.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prescriptions")
public class Prescription {
    // 1. 'id' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the unique identifier for each prescription.
    //      - The @Id annotation marks it as the primary key in the MongoDB collection.
    //      - The id is of type String, which is commonly used for MongoDB's ObjectId as it stores IDs as strings in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // 2. 'patientName' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the name of the patient receiving the prescription.
    //      - The @NotNull annotation ensures that the patient name is required.
    //      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters, ensuring a reasonable name length.
    @NotNull(message = "Patient name cannot be null")
    @Size(min = 3, max = 100)
    private String patientName;

    // 3. 'appointmentId' field:
    //    - Type: private Long
    //    - Description:
    //      - Represents the ID of the associated appointment where the prescription was given.
    //      - The @NotNull annotation ensures that the appointment ID is required for the prescription.
    @NotNull(message = "Appointment id cannot be null")
    private Long appointmentId;

    // 4. 'medication' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the medication prescribed to the patient.
    //      - The @NotNull annotation ensures that the medication name is required.
    //      - The @Size(min = 3, max = 100) annotation ensures that the medication name is between 3 and 100 characters, which ensures meaningful medication names.
    @NotNull(message = "Medication name cannot be null")
    @Size(min = 3, max = 100)
    private String medication;

    // 5. 'dosage' field:
    //    - Type: private String
    //    - Description:
    //      - Represents the dosage information for the prescribed medication.
    //      - The @NotNull annotation ensures that the dosage information is provided.
    @NotNull(message = "Dosage cannot be null")
    @Size(min = 3, max = 20)
    private String dosage;

    // 6. 'doctorNotes' field:
    //    - Type: private String
    //    - Description:
    //      - Represents any additional notes or instructions from the doctor regarding the prescription.
    //      - The @Size(max = 200) annotation ensures that the doctor's notes do not exceed 200 characters, providing a reasonable limit for additional notes.
    @NotNull(message = "Doctor notes cannot be null")
    private String doctorNotes;

    //@NotNull(message = "Pharmacy name cannot be null")
    @Size(min = 3, max = 100)
    private String pharmacyName;

    // 7. Constructors:
    //    - The class includes a no-argument constructor (default constructor) and a parameterized constructor that initializes the fields: patientName, medication, dosage, doctorNotes, and appointmentId.

    public Prescription() {
    }

    public Prescription(String id, String patientName, Long appointmentId, String medication, String dosage, String doctorNotes, String pharmacyName) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
        this.pharmacyName = pharmacyName;
    }

    // 8. Getters and Setters:
    //    - Standard getter and setter methods are provided for all fields: id, patientName, medication, dosage, doctorNotes, and appointmentId.
    //    - These methods allow access and modification of the fields of the Prescription class.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}

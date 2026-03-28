package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id") // Add this line to match your MySQL column
    private Long id;

    @Column(unique = true)
    private String rollNo;

    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "First name must be at least 3 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Last name must be at least 3 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private String gender;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Min(value = 1)
    @Max(value = 12, message = "Class must be between 1 and 12")
    private int classLevel;

    private LocalDate regDate = LocalDate.now();
    private String status = "ACTIVE";

    // Constructor to auto-generate the unique Roll Number
    public Student() {
        this.rollNo = "STU-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getClassLevel() { return classLevel; }
    public void setClassLevel(int classLevel) { this.classLevel = classLevel; }
    public LocalDate getRegDate() { return regDate; }
    public void setRegDate(LocalDate regDate) { this.regDate = regDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
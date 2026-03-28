package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        // Business Logic: Duplicate Email Check
        Optional<Student> existing = studentRepository.findByEmail(student.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
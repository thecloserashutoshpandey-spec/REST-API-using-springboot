package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Student student, BindingResult result) {

        // Handle Validation Errors (Phone length, Name size, etc.)
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        // Handle Service Logic (Duplicate Email)
        try {
            Student savedStudent = studentService.registerStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("email", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.services.InternshipRepository;
import com.example.demo.entity.Internship;
import com.example.demo.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository internshipRepository;

    @PostMapping
    public ResponseEntity<Student> createInternship(@RequestBody Student student) {
        Student savedInternship = internshipRepository.save(student);
        return new ResponseEntity<>(savedInternship, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllInternships() {
       return internshipRepository.findAll();

    }
}
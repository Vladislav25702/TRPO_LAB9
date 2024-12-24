package com.example.demo.controller;

import com.example.demo.services.InternshipRepository;
import com.example.demo.entity.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships") //Изменено на /api/internships
@CrossOrigin(origins = "http://localhost:3000") //Разрешаем CORS
public class InternshipController {

    @Autowired
    private InternshipRepository internshipRepository;

    @PostMapping
    public ResponseEntity<Internship> createInternship(@RequestBody Internship internship) {
        Internship savedInternship = internshipRepository.save(internship);
        return new ResponseEntity<>(savedInternship, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @GetMapping("/search")
    public List<Internship> searchInternships(@RequestParam(value = "query", required = false) String query) {
        Specification<Internship> spec = (root, query1, criteriaBuilder) -> {
            if (query == null || query.isEmpty()) {
                return null;
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%" + query + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + query + "%")
            );
        };
        return internshipRepository.findAll(spec);
    }
}
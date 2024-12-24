package com.example.demo.services;

import com.example.demo.entity.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    @Autowired
    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }




    public Internship getInternshipById(Long internshipId) {
        Optional<Internship> internshipOptional = internshipRepository.findById(internshipId);
        return internshipOptional.orElse(null); // or throw an exception if you want to handle cases when internship does not exist
    }
}

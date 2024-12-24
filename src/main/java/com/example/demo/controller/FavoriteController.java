package com.example.demo.controller;


import com.example.demo.entity.Internship;
import com.example.demo.entity.InternshipFavoriteEntity;
import com.example.demo.entity.Student;
import com.example.demo.services.InternshipFavoriteService;
import com.example.demo.services.InternshipService;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FavoriteController {

private final InternshipFavoriteService internshipFavoriteService;
private final InternshipService internshipService;
private final StudentService studentService;

@Autowired
public FavoriteController(InternshipFavoriteService internshipFavoriteService, InternshipService internshipService, StudentService studentService) {
        this.internshipFavoriteService = internshipFavoriteService;
        this.internshipService = internshipService;
        this.studentService = studentService;
        }

@PostMapping("/internships/favorite/add/{internshipId}")
public String addToFavorites(@PathVariable("internshipId") Long internshipId, Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        Student student = studentService.getStudentById(userId);
        Internship internship = internshipService.getInternshipById(internshipId);
        if(internship != null && student != null) {
        internshipFavoriteService.addToFavorites(internship, student);
        }
        return "redirect:/internships/all";
        }
@PostMapping("/internships/favorite/remove/{internshipId}")
public String removeFromFavorites(@PathVariable("internshipId") Long internshipId, Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        Student student = studentService.getStudentById(userId);
        Internship internship = internshipService.getInternshipById(internshipId);
        if(internship != null && student != null) {
        internshipFavoriteService.removeFromFavorites(internship, student);
        }
        return "redirect:/internships/all";
        }
@GetMapping("/profile/favorites")
public String showFavoriteInternships(Authentication authentication, Model model){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        Student student = studentService.getStudentById(userId);
        if(student != null){
        List<InternshipFavoriteEntity> favoriteInternships = internshipFavoriteService.getFavoriteInternships(userId);
        model.addAttribute("favoriteInternships", favoriteInternships);
        model.addAttribute("student", student);
        }
        return "favorites";
        }

        }
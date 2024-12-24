package com.example.demo.controller;


import com.example.demo.entity.ResumeEntity;

import com.example.demo.services.ResumeService;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login2";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }



    @GetMapping("main")
  //  @PreAuthorize("hasAuthority('admin')")
    public String user(Model model, Authentication authentication) {
    //public String user(Model model, Authentication authentication) {
       // model.addAttribute("allUsers", userService.findAllUsers());
        return "index";
    }

    @GetMapping("profile/profile")
    //  @PreAuthorize("hasAuthority('admin')")
    public String user2(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
            // Добавьте другие данные
            model.addAttribute("email", userDetails.getStudent().getEmail()); // Пример доступа к полям из Student entity
           model.addAttribute("firstName", userDetails.getStudent().getFirstName());
            model.addAttribute("lastName", userDetails.getStudent().getLastName());
            List<ResumeEntity> resumes = resumeService.getResumesByUserId(userDetails.getStudent().getId());
            model.addAttribute("resumes", resumes);
        }
        return "index3";
    }

    //@GetMapping("profile/user")
    //@PreAuthorize("hasAuthority('user') || hasAuthority('admin')")
   // public String user(Model model, Authentication authentication) {
       // model.addAttribute("allSections", sectionsService.loadAllSections());
       // model.addAttribute("allProducts", productService.loadAllBooks());
       // return "welcomeuser";
    //}


}





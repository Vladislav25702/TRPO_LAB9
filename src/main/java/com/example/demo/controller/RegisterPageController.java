package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import com.example.demo.services.StudentRegistrationDTO;
import com.example.demo.services.StudentService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

   // @PostMapping("register")
   // public String registerPost(@ModelAttribute Users user) {
      //  if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
         //   return "redirect:/register?error";
       // }
           // userService.addUser(user);
         //   return "redirect:/login";
        //}
   @PostMapping("/register")
   public ResponseEntity<String> registerStudent(
           @RequestParam("firstName") String firstName,
           @RequestParam("lastName") String lastName,
           @RequestParam("email") String email,
           @RequestParam("username") String username,
           @RequestParam("password") String password

   ) {
       try {
           StudentRegistrationDTO request = new StudentRegistrationDTO(firstName, lastName, email, username, password);
           studentService.registerStudent(request);
           return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
       } catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
   }
    }




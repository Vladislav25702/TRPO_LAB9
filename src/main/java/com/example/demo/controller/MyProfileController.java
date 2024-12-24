package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import com.example.demo.services.ProfileUpdateDTO;
import com.example.demo.services.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MyProfileController {

    private final StudentService studentService;

    public MyProfileController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("profile/edit")
    public String showProfileEditForm(Authentication authentication, Model model) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        ProfileUpdateDTO profileUpdateDto = new ProfileUpdateDTO();
      // profileUpdateDto.setFirstName(userDetails.getFirstName());
      // profileUpdateDto.setLastName(userDetails.getLastName());
      // profileUpdateDto.setEmail(userDetails.getUser().getEmail());
        model.addAttribute("profileUpdateDto", profileUpdateDto);
        return "editprofile"; // Имя вашей HTML-страницы с формой
    }

    @PostMapping("profile/edit")
    public String submitProfileEditForm(@Valid @ModelAttribute("profileUpdateDto") ProfileUpdateDTO profileUpdateDto,
                                        BindingResult bindingResult, Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            return "index3";
        }
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Users user = userDetails.getUser();
        Student student = userDetails.getStudent();


       student.setFirstName(profileUpdateDto.getFirstName());
        student.setLastName(profileUpdateDto.getLastName());
        student.setEmail(profileUpdateDto.getEmail());
        studentService.updateStudent(student); // Вызов метода сервиса для обновления
        model.addAttribute("message", "Профиль успешно обновлен!");
        return "redirect:/profile/profile"; // Редирект на страницу профиля
    }
}
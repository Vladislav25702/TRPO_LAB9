package com.example.demo.services;



import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDAO extends UserDetailsService {
    Users findByUserName(String firstName);

   Users addUser(Users user);

    List<Users> findAllUsers();

    // @Override
   // public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
     //   return null;
   // }

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;



}
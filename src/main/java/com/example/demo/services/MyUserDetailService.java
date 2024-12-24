package com.example.demo.services;

import com.example.demo.controller.MyUserDetails;
import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailService {

    @Autowired
    UserRepository entityRepository;

    @Autowired
    StudentRepository studentRepository;
  //  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     //   Optional<Users> userOptional = entityRepository.findByUsername(username);

      //  if(userOptional.isEmpty()){
       //     throw new UsernameNotFoundException("User not found");
      //  }
      //  Users user = userOptional.get();
       // if(user.getRole() == Users.Role.STUDENT){
          //  Student student = studentRepository.findByUserId(user.getId());
        //    return new MyUserDetails(user, student);
       // }else{
        //    return new MyUserDetails(user);
      //  }
  //  }
}


package com.example.demo.services;


import com.example.demo.controller.MyUserDetails;
import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements UserDAO {
    @Autowired
    UserRepository entityRepository;
    @Autowired
    StudentRepository studentRepository;

    public UserService() {
    }

    @Override
    public Users findByUserName(String username) {
        return entityRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Users addUser(Users user) {
        return entityRepository.save(user);
    }

    @Override
    public List<Users> findAllUsers() {
        return (List<Users>) entityRepository.findAll();
    }


    // @Override
    // public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
    //   return null;
    // }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userOptional = entityRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        Users user = userOptional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getRole().name();
        authorities.add(new SimpleGrantedAuthority(role));
        Student student = null;
        if(role.equals("STUDENT")){
            student = studentRepository.findByUserId(user.getId());
        }
        return new MyUserDetails(user, student, authorities);
    }



    public UserService(UserRepository studentRepository) {
        this.entityRepository = studentRepository;
    }
    public void updateStudent(Users student) {
        entityRepository.save(student);
    }
    public Users getUserById(Long id) {
        return entityRepository.findById(id)
                .orElse(null); // Возвращает null, если студент не найден
    }


}



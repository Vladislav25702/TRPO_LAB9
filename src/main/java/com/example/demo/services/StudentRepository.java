package com.example.demo.services;


import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByFirstName(String firstName);

    Student findByEmail(String email);

    Student findByUserId(Long id);

    Optional<Student> findByUser(Users user);
}


package com.example.demo;

import com.example.demo.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Demo2Application {


    public static void main(String[] args) {


        SpringApplication.run(Demo2Application.class, args);

    }

}

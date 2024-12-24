package com.example.demo.services;

import com.example.demo.entity.ExpirienceEntity;
import com.example.demo.entity.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<ExpirienceEntity, Long> {

}
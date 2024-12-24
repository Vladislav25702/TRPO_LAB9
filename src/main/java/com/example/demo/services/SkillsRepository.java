package com.example.demo.services;

import com.example.demo.entity.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<SkillsEntity, Long> {
    Optional<SkillsEntity> findByName(String name);
}
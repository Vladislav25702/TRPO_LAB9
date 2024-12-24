
package com.example.demo.services;

import com.example.demo.entity.InternshipFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipFavoriteRepository extends JpaRepository<InternshipFavoriteEntity, Integer> {
    List<InternshipFavoriteEntity> findByStudentId(Long studentId);
    boolean existsByInternshipsIdAndStudentId(Long internshipsId, Long studentId);
    void deleteByInternshipsIdAndStudentId(Long internshipsId, Long studentId);
}
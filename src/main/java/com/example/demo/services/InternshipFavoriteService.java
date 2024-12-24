package com.example.demo.services;

import com.example.demo.entity.Internship;
import com.example.demo.entity.InternshipFavoriteEntity;
import com.example.demo.entity.Student;
import com.example.demo.services.InternshipFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InternshipFavoriteService {

    private final InternshipFavoriteRepository internshipFavoriteRepository;

    @Autowired
    public InternshipFavoriteService(InternshipFavoriteRepository internshipFavoriteRepository) {
        this.internshipFavoriteRepository = internshipFavoriteRepository;
    }

    @Transactional
    public void addToFavorites(Internship internship, Student student) {
        if(!internshipFavoriteRepository.existsByInternshipsIdAndStudentId(internship.getId(),student.getId())){
            InternshipFavoriteEntity favorite = new InternshipFavoriteEntity();
            favorite.setInternships(internship);
            favorite.setStudent(student);
            internshipFavoriteRepository.save(favorite);
        }
    }
    @Transactional
    public void removeFromFavorites(Internship internship, Student student){
        if(internshipFavoriteRepository.existsByInternshipsIdAndStudentId(internship.getId(), student.getId())) {
            internshipFavoriteRepository.deleteByInternshipsIdAndStudentId(internship.getId(), student.getId());
        }
    }
    public List<InternshipFavoriteEntity> getFavoriteInternships(Long studentId){
        return internshipFavoriteRepository.findByStudentId(studentId);
    }
    public boolean checkIfIsFavorite(Long internshipsId, Long studentId){
        return internshipFavoriteRepository.existsByInternshipsIdAndStudentId(internshipsId, studentId);
    }
}
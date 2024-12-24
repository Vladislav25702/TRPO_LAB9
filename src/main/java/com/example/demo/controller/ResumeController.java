package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ResumeController {

    private final ResumeService resumeService;
    private final StudentService studentService;

    private final SkillService skillService;

    private final EducationService educationService;

    private final ExperienceService experienceService;

    public ResumeController(ResumeService resumeService, StudentService studentService, SkillService skillService, EducationService educationService, ExperienceService experienceService) {
        this.resumeService = resumeService;
        this.studentService = studentService;
        this.skillService = skillService;
        this.educationService = educationService;
        this.experienceService = experienceService;
    }


    @GetMapping("/resume/create")
    public String showResumeCreationForm(Model model) {
        model.addAttribute("resumeCreationDto", new ResumeCreationDTO());
        return "resume-create";
    }

    @PostMapping("/resume/create")
    public String submitResumeCreationForm(@Valid @ModelAttribute("resumeCreationDto") ResumeCreationDTO resumeCreationDto,
                                           BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "resume-create";
        }

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getStudent().getId();

        ResumeEntity resume = new ResumeEntity();
        resume.setInformation(resumeCreationDto.getInformation());
        resume.setName(resumeCreationDto.getName());
        resume.setSurname(resumeCreationDto.getSurname());
        resume.setOt(resumeCreationDto.getOt());
        resume.setPhone(resumeCreationDto.getPhone());
        resume.setEmail(resumeCreationDto.getEmail());
        resume.setAddress(resumeCreationDto.getAddress());



        List<EducationEntity> educationEntities = resumeCreationDto.getEducationEntities();
        if (educationEntities != null) {
            for (EducationEntity education : educationEntities) {
                education.setResume(resume);
            }
            resume.setEducations(educationEntities);
        }


        // Set the resume on the experience entities
        List<ExpirienceEntity> experienceEntities = resumeCreationDto.getExpirienceEntities();
        if(experienceEntities != null) {
            for (ExpirienceEntity experience: experienceEntities) {
                experience.setResume(resume);
            }
            resume.setExperiences(experienceEntities);
        }

        List<String> skillNames = resumeCreationDto.getSkillNames();
        if(skillNames != null && !skillNames.isEmpty()){
            List<SkillsEntity> skills = new ArrayList<>();
            for(String skillName : skillNames){
                // Check if the skill already exists
                SkillsEntity existingSkill = skillService.findSkillsEntityByName(skillName);
                if(existingSkill != null){
                    skills.add(existingSkill);
                }
                else {
                    // Create a new skills entity and save it
                    SkillsEntity skillsEntity = new SkillsEntity();
                    skillsEntity.setName(skillName);
                    SkillsEntity savedSkill = skillService.saveSkill(skillsEntity);
                    skills.add(savedSkill);
                }
            }
            resume.setSkills(skills);
        }

        Student student = studentService.getStudentById(userId);
        resume.setStudent(student);
        resumeService.createResume(resume);
        return "redirect:/profile/profile";
    }


    @GetMapping("/resume/update/{id}")
    public String showEditResumeForm(@PathVariable("id") Long resumeId, Model model, Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getStudent().getId();

        ResumeEntity resume = resumeService.getResumeById(resumeId);
        if (resume == null || !Objects.equals(resume.getStudent().getId(), userId)) {
            return "redirect:/profile/profile?error=resumeNotFound";
        }

        ResumeCreationDTO resumeCreationDTO = new ResumeCreationDTO();
        resumeCreationDTO.setId(resume.getId());
        resumeCreationDTO.setInformation(resume.getInformation());

        if(resume.getPersonalInformation() != null){
            resumeCreationDTO.setPersonalInformation(resume.getPersonalInformation());
        }

        if(resume.getEducations() != null){
            List<EducationEntity> educationEntities = new ArrayList<>();
            for(EducationEntity education : resume.getEducations()){
                educationEntities.add(education);
            }
            resumeCreationDTO.setEducationEntities(educationEntities);
        }

        if(resume.getExperiences() != null){
            List<ExpirienceEntity> experienceEntities = new ArrayList<>();
            for(ExpirienceEntity experience : resume.getExperiences()){
                experienceEntities.add(experience);
            }
            resumeCreationDTO.setExpirienceEntities(experienceEntities);
        }

        if (resume.getSkills() != null){
            List<String> skillNames = new ArrayList<>();
            for(SkillsEntity skill: resume.getSkills()){
                skillNames.add(skill.getName());
            }
            resumeCreationDTO.setSkillNames(skillNames);
        }
        model.addAttribute("resumeCreationDto", resumeCreationDTO);
        model.addAttribute("resumeId", resumeId);
        return "resume-edit";
    }


    @PostMapping("/resume/update/{id}")
    public String updateResume(@PathVariable("id") Long resumeId,
                               @Valid @ModelAttribute("resumeCreationDto") ResumeCreationDTO resumeCreationDto,
                               BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "resume-edit";
        }

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getStudent().getId();

        try {

            ResumeEntity resume = resumeService.getResumeById(resumeId);
            System.out.println(resumeId);
            if (resume == null || !Objects.equals(resume.getStudent().getId(), userId)) {

                return "redirect:/profile/profile?error=resumeNotFound";
            }
            resumeService.updateResume(resume, resumeCreationDto);

        } catch (RuntimeException e){
            return "redirect:/profile/profile?error=userDoesNotOwnResume";
        }

        return "redirect:/profile/profile";
    }

   @GetMapping("/resumes")
    public String showAllResumes(Model model) {
        List<ResumeEntity> resumes = resumeService.getAllResume();
       model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/resume/{id}")
    public String showResumeDetails(@PathVariable Long id, Model model) {
        ResumeEntity resume = resumeService.getResumeById(id);
        if (resume == null) {
            return "error";
        }
        model.addAttribute("resume", resume);
        return "resume-view";
    }

    @PostMapping("/resume/delete/{id}")
    public String deleteResume(@PathVariable("id") Long resumeId, Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getStudent().getId();
        try {
            ResumeEntity resume = resumeService.getResumeById(resumeId);
            if (resume == null || !Objects.equals(resume.getStudent().getId(), userId)) {
                return "redirect:/profile/profile?error=resumeNotFound";
            }
            resumeService.deleteResume(resumeId);
        }
        catch (RuntimeException e){
            return "redirect:/profile/profile?error=userDoesNotOwnResume";
        }
        return "redirect:/profile/profile";
    }



}
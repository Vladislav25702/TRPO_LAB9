package com.example.demo.services;




import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import com.example.demo.services.StudentRepository;
import com.example.demo.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElse(null); // Возвращает null, если студент не найден
    }

    @Transactional
    public void registerStudent(StudentRegistrationDTO registration) {
        if (userRepository.existsByUsername(registration.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        Users user = new Users();
        user.setUsername(registration.getUsername());
        user.setPassword(registration.getPassword());
        user.setRole(Users.Role.STUDENT);

        Users savedUser = userRepository.save(user);

        Student student = new Student();
        student.setFirstName(registration.getFirstName());
        student.setLastName(registration.getLastName());
        student.setEmail(registration.getEmail());
        student.setUser(savedUser);

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student student){
        studentRepository.save(student);
    }
}
package com.learningspringboot.spring.data.jpa.service;

import com.learningspringboot.spring.data.jpa.entity.Student;
import com.learningspringboot.spring.data.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String addStudent(Student s) {
        studentRepository.save(s);
        return "Student added.";
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        // return studentRepository.findByFirstName(firstName);
        return studentRepository.findByFirstNameIgnoreCase(firstName);
    }

    public List<Student> getStudentsByGuardianName(String guardianName) {
        return studentRepository.findByGuardianName(guardianName);
    }

    public Student getStudentByEmailAddress(String mail) {
        return studentRepository.getStudentByEmailAddress(mail);
    }

    public int updateFirstNameByEmailId(String firstName, String emailId) {
        return studentRepository.updateStudentNameByEmailId(firstName, emailId);
    }
}

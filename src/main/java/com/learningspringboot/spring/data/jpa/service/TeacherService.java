package com.learningspringboot.spring.data.jpa.service;

import com.learningspringboot.spring.data.jpa.entity.Teacher;
import com.learningspringboot.spring.data.jpa.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public String addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return("New teacher added successfully.");
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}

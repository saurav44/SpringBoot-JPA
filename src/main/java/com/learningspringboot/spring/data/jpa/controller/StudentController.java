package com.learningspringboot.spring.data.jpa.controller;

import com.learningspringboot.spring.data.jpa.entity.Student;
import com.learningspringboot.spring.data.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public String addStudent(@RequestBody Student s) {
        return studentService.addStudent(s);
    }

    @GetMapping(path = "{id}")              // path and pathVariable parameter should be same
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping(path = "name/{name}")
    public List<Student> getStudentsByFirstName(@PathVariable("name") String firstName) {
        return studentService.getStudentsByFirstName(firstName);
    }

    @GetMapping(path = "guardian/{name}")
    public List<Student> getStudentsByGuardianName(@PathVariable("name") String guardianName) {
        return studentService.getStudentsByGuardianName(guardianName);
    }

    @GetMapping(path = "email/{mail}")
    public Student getStudentByEmailAddress(@PathVariable("mail") String mail) {
        return studentService.getStudentByEmailAddress(mail);
    }

    @PutMapping(path = "updatename")
    public int updateFirstNameByEmailId(@RequestBody Student stu) {
        return studentService.updateFirstNameByEmailId(stu.getFirstName(), stu.getEmailId());
    }
}

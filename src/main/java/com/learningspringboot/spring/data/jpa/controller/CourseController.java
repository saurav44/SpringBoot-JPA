package com.learningspringboot.spring.data.jpa.controller;

import com.learningspringboot.spring.data.jpa.entity.Course;
import com.learningspringboot.spring.data.jpa.entity.CourseMaterial;
import com.learningspringboot.spring.data.jpa.error.CourseNotFoundException;
import com.learningspringboot.spring.data.jpa.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Logger from slf4j
    private final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @PostMapping(path = "course")
                         // validation given in entity i.e for credit
    public Course addCourse(@Valid @RequestBody Course course) {
        LOGGER.info("Inside addCourse of CourseController");
        return courseService.addCourse(course);
    }

    @PostMapping(path = "material")
    public String addCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        LOGGER.info("Inside addCourseMaterial of CourseController");
        return courseService.addCourseMaterial(courseMaterial);
    }

    @GetMapping("materials")
    public List<CourseMaterial> getAllCourseMaterials() {
        return courseService.getAllCourseMaterials();
    }

    @GetMapping()
    public List<Course> getAllCourses() {
        LOGGER.info("Inside getAllCourses of CourseController");
        return courseService.getAllCourses();
    }

    @GetMapping(path = "{id}")
    public Course getCourseById(@PathVariable("id") Long courseId) throws CourseNotFoundException {
        return courseService.getCourseById(courseId);
    }

    @GetMapping(path = "name")
    public Course getCourseByTitle(@PathVariable("title") String title) {
        return courseService.getCourseByTitle(title);
    }

    @GetMapping(path = "url/{url}")
    public String getCourseTitleByUrl(@PathVariable("url") String url) {
        return courseService.getCourseTitleByUrl(url);
    }

    // update course
    @PutMapping(path = "{id}")
    public Course updateCourse(@PathVariable("id") Long courseId, @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }



    @GetMapping(path= "title/{title}")
    public String getUrlByCourseTitle(@PathVariable("title") String title) {
        return courseService.getUrlByCourseTitle(title);
    }

    // OneToMany/ ManyToOne
    @PostMapping(path = "withteacher")
    public String saveCourseWithTeacher(@RequestBody Course course) {
        return courseService.saveCourseWithTeacher(course);
    }

    // Pagination
    @GetMapping("page")
    public List<Course> findAllPagination() {
        return courseService.findAllPagination();
    }

    // Pagination and Sorting
    @GetMapping(path = "pagesort")
    public List<Course> findAllPaginationAndSorting() {
        return courseService.findAllPaginationAndSorting();
    }

    // Just try
    @GetMapping(path = "titlewithp")
    public List<Course> findAllTitleContainingP() {
        return courseService.findAllTitleContainingP();
    }

    // ManyToMany
    @PostMapping(path = "coursewithstudentandteacher")
    public String addCourseWithStudentAndTeacher(@RequestBody Course course) {
        return courseService.addCourseWithStudentAndTeacher(course);
    }
}

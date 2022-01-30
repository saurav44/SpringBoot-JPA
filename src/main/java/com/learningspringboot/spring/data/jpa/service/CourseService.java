package com.learningspringboot.spring.data.jpa.service;

import com.learningspringboot.spring.data.jpa.entity.Course;
import com.learningspringboot.spring.data.jpa.entity.CourseMaterial;
import com.learningspringboot.spring.data.jpa.error.CourseNotFoundException;
import com.learningspringboot.spring.data.jpa.repository.CourseMaterialRepository;
import com.learningspringboot.spring.data.jpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course course) {
        Course c = courseRepository.findById(courseId).get();

        if(Objects.nonNull(course.getTitle()) &&
                !"".equalsIgnoreCase(course.getTitle())) {
            c.setTitle(course.getTitle());
        }

        if(Objects.nonNull(course.getCredit()) &&
                !"".equalsIgnoreCase(course.getCredit())) {
            c.setCredit(course.getCredit());
        }

        if(Objects.nonNull(course.getCourseMaterial())) {
            c.setCourseMaterial(course.getCourseMaterial());
        }

        if(Objects.nonNull(course.getTeacher())) {
            c.setTeacher(course.getTeacher());
        }

        if(Objects.nonNull(course.getStudents())) {
            c.setStudents(course.getStudents());
        }

        return courseRepository.save(c);

    }

    public String addCourseMaterial(CourseMaterial courseMaterial) {
        courseMaterialRepository.save(courseMaterial);
        return "Course Material Added Successfully";
    }

    public List<CourseMaterial> getAllCourseMaterials() {
        return courseMaterialRepository.findAll();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public String getCourseTitleByUrl(String url) {
        CourseMaterial cm = courseMaterialRepository.findByUrl(url);
        String title = cm.getCourse().getTitle();
        return title;
    }

    public String getUrlByCourseTitle(String title) {
        return courseRepository.findByTitle(title).getCourseMaterial().getUrl();
    }

    public String saveCourseWithTeacher(Course course) {
        courseRepository.save(course);
        return "Course added with teacher.";
    }

    // Pagination and Sorting

    public List<Course> findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(2,2 );


        Long totalCourses = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        int totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("Total Courses: " + totalCourses);
        System.out.println("Total Pages: " + totalPages);

        return courseRepository.findAll(secondPageWithTwoRecords)
                .getContent();
    }

    public List<Course> findAllPaginationAndSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                4,
                Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0,
                5,
                Sort.by("credit").descending()
        );

        Pageable sortByTitleDescAndCreditAsc = PageRequest.of(
                0,
                5,
                Sort.by("title")
                        .descending()
                        .and(Sort.by("credit"))
        );

        return courseRepository.findAll(sortByTitleDescAndCreditAsc).getContent();
    }

    public List<Course> findAllTitleContainingP() {
        Pageable titleContainingP = PageRequest.of(
                0, 2
        );

        return courseRepository.findByTitleContaining("p", titleContainingP);
    }

    public String addCourseWithStudentAndTeacher(Course course) {
        courseRepository.save(course);
        return "Course with student and teacher added successfully.";
    }


    // Exceptional Handling
    public Course getCourseById(Long courseId) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);

        if(!course.isPresent()) {
            throw new CourseNotFoundException("Course Not Available");
        }

        return course.get();
    }

    public Course getCourseByTitle(String title) {
        return courseRepository.findByTitle(title);
    }
}

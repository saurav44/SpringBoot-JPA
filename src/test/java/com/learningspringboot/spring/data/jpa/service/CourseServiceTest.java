package com.learningspringboot.spring.data.jpa.service;

import com.learningspringboot.spring.data.jpa.entity.Course;
import com.learningspringboot.spring.data.jpa.error.CourseNotFoundException;
import com.learningspringboot.spring.data.jpa.repository.CourseRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    // mocking the repository i.e creating own data and testing on it
    // data won't be searched in actual database
    @MockBean
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course =
                Course.builder()
                .courseId(41L)
                .title("nepali")
                .credit("4")
                .build();

        Mockito.when(courseRepository.findByTitle("nepali"))
                .thenReturn(course);

    }

    @Test
    @DisplayName("Get data based on valid title")       // to dispaly proper test name in report
    // @Disabled can be used to skip testing that method
    public void whenValidCourseName_thenCourseShouldFound() {
        String courseTitle = "nepali";
        Course found = courseService.getCourseByTitle(courseTitle);
        assertEquals(courseTitle, found.getTitle());
    }
}
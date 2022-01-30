package com.learningspringboot.spring.data.jpa.controller;

import com.learningspringboot.spring.data.jpa.entity.Course;
import com.learningspringboot.spring.data.jpa.error.CourseNotFoundException;
import com.learningspringboot.spring.data.jpa.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        // output course
        course = Course.builder()
                .title("Machine Learning")
                .credit("4")
                .courseId(4L)
                .build();
    }

    @Test
    void addCourse() throws Exception {
        // no id input course
        Course inputCourse = Course.builder()
                .title("Machine Learning")
                .credit("4")
                .build();

        Mockito.when(courseService.addCourse(inputCourse))
                .thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"title\": \"DSA\",\n" +
                "    \"credit\": 7\n" +
                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCourseById() throws Exception {
        Mockito.when(courseService.getCourseById(1L))
                .thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(("$.title")).
                    value(course.getTitle()));
    }
}
package com.learningspringboot.spring.data.jpa.repository;

import com.learningspringboot.spring.data.jpa.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest            // data will be added to database temporarily i.e removed after testing
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;    // to save and persist data

    @BeforeEach
    void setUp() {
        Course course =
                Course.builder()
                        .title("health")
                        .credit("2")
                        .build();

        entityManager.persist(course);
    }

    @Test
    public void whenFindById_thenReturnCourse() {
        Course course = courseRepository.findById(1L).get();
        assertEquals(course.getTitle(), "health");

    }
}
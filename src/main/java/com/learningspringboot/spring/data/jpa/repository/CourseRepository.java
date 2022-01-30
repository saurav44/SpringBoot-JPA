package com.learningspringboot.spring.data.jpa.repository;

import com.learningspringboot.spring.data.jpa.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByTitle(String title);

    List<Course> findByTitleContaining(String title, Pageable pageRequest);
}

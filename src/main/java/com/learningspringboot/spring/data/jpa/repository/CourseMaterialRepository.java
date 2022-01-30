package com.learningspringboot.spring.data.jpa.repository;

import com.learningspringboot.spring.data.jpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    CourseMaterial findByUrl(String url);
}

package com.learningspringboot.spring.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    private String url;

    @OneToOne(
            cascade = CascadeType.ALL,                   // cascading two tables
            fetch = FetchType.EAGER,
            // fetch data: i. eager - data from another table is loaded  ii.lazy - load data only when called
            optional = false                            // course cannot be saved without courseMaterial
    )
    @JoinColumn(
            name = "course_id",                         // name for the foreign key
            referencedColumnName = "courseId"           // column in Course which in being referenced
    )
    @JsonIgnore
    // when used, nested data not visible. solves infinite recursion in postman
    private Course course;

}

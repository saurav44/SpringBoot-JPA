package com.learningspringboot.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data                     // Lombok - no need to write getters, setters, toString methods
@AllArgsConstructor       // All parameter constructor
@NoArgsConstructor        // No parameter constructor
@Builder                   // with any no. of properties we can create object, with constructor either // all properties or no properties are required
public class Course {

    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;

    private String title;

    // Hibernet Validation
    @NotBlank(message = "Please add course credit")
    @Size(min = 0, max = 4)
    // @Length(min=5,max=10) @Email, @Positive, @Negative, @PositiveOrZero
    // for DATE @Future, @Past, @FutureOrPresent, @PastOrPresent
    private String credit;

    @OneToOne(
            mappedBy = "course"             // this course in used in CourseMaterial to join
    )
    private CourseMaterial courseMaterial;
//    we cannot @JoinColumn here because it is used in another columns. so use mappedBy as used above
//    @JoinColumn(
//            name = "course_material_id",
//            referencedColumnName = "courseMaterialId"
//    )

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    // @JoinTable is required instead of @JoinColumn
    // for "many to many" mapping new table should be created
    @JoinTable(
            name = "student_course_map",
            // join column should be itself
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            // inverse join should be another table
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

}

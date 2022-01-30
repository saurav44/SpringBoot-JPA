package com.learningspringboot.spring.data.jpa.entity;

import javax.persistence.*;

@Entity
// by default table name will be student, to change the table name use @Table(name="...")
@Table(
        name="tbl_student",
        // defining unique constraints
        uniqueConstraints = @UniqueConstraint(
                name = "unique_constrains",
                columnNames = "email_address"
        )
)
public class Student {

    // for primary key use @Id
    @Id
    @SequenceGenerator(       // to generate primary key value automatically i.e sequentally
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    // by default column name will be emailId, to change the column name use @Column(name="...")
    @Column(
            name="email_address",
            nullable = false
    )
    private String emailId;

    // Since its table won't be created and data will be present in Student table, use Embedded
    @Embedded
    private Guardian guardian;

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

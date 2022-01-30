package com.learningspringboot.spring.data.jpa.repository;

import com.learningspringboot.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // creating JPA method
    // format: returnType findBy______(type parameter)
    List<Student> findByFirstName(String firstName);

    // to ignore the case, user IgnoreCase after above format i.e findBy_____IgnoreCase
    List<Student> findByFirstNameIgnoreCase(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);



    // JPQL Query
    // based on the class and attributes not according to table and column name
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    Student getStudentFirstNameByEmailAddress(String email);


    
    // Native Query:
    // required for complex operation
    // use table and column name
    @Query(
            value="SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);



    // Named Parameters,
    // denoted by :parameter_name
    @Query("select s from Student s where s.emailId = :email")
    Student getStudentByEmailAddressNamedParameter(@Param("email") String email);



    @Modifying              // used when updating or deleting a data
    @Transactional          // to create a transaction  
    // after transaction successful, it is committed to DB otherwise rollback
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String name, String email);

}

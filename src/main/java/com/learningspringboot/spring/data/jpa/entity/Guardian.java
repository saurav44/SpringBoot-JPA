package com.learningspringboot.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// If we don't want to create new table i.e guardian, use Embeddable. It's data is stored in Student table.
@Embeddable
// to provide getters and setters
@Data
@AllArgsConstructor
@NoArgsConstructor

// we can override the name of column i.e make name as guardian_name
@AttributeOverrides({
        @AttributeOverride(
                name = "name",                               // name given in Guardian class/entity
                column = @Column(name = "guardian_name")     // name we want to give
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;
}

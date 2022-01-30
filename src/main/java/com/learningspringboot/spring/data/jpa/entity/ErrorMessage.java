package com.learningspringboot.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
// it is also know as POJO class - allArgs, noArgs constructor and getters and setters
public class ErrorMessage {

    private HttpStatus status;
    private String message;
}

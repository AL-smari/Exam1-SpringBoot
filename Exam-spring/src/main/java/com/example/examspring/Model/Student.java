package com.example.examspring.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "id should not be Empty")
    private String id;
    @NotEmpty(message = "name should not be Empty")
    private String name;
    @NotNull(message = "age should not be null")
    private int age;
    @NotEmpty(message = "major should not be Empty")
    private String major;

}

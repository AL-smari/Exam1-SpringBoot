package com.example.examspring.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "id should not by Empty")
    private String id;
    @NotEmpty(message = "name should not be Empty")
    private String name;
    @NotNull(message = "salary should not be Empty")
    private double salary;
}

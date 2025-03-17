package com.example.EmployeePayrollApp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z\\s]{3,50}$",message = "Name between  3 and 50 characters")
    private String name;

    @NotEmpty(message = "Department cannot  be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z\\s]{3,50}$",message = "Name between  3 and 50 characters")
    private String department;
    @NotNull(message = "Salary cannot be null")
    @Min(value=1000 ,message = "Salary must be at least 10000")
    @Max(value = 200000, message ="Salary must be at most 10000")
    private double salary;
}

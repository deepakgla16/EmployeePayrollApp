package com.example.EmployeePayrollApp.interfaces;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.Employee;

import java.util.List;

public interface EmployeeInterface {
    List<Employee> getAllEmployees();
    Employee addEmployee(EmployeeDTO empDTO);
    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, EmployeeDTO empDTO);
    void deleteEmployee(Long id);
}

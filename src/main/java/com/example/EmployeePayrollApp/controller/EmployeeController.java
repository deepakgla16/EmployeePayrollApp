package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.Exception.ResourceNotFoundException;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDTO empDTO) {
        log.info("Request for adding employee: {}", empDTO);
        Employee employee = employeeService.addEmployee(empDTO);
        log.info("Employee added successfully with ID: {}", employee.getId());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        log.info("Total employees found: {}", employees.size());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = employeeService.getEmployeeById(id);

        if (employee != null) {
            log.info("Employee found: {}", employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            log.warn("Employee with ID {} not found", id);
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO empDTO) {
        log.info("Request to update employee with ID: {}", id);
        Employee employee = employeeService.updateEmployee(id, empDTO);
        log.info("Employee updated successfully");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("Request to delete employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        log.info("Employee with ID {} deleted successfully", id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}

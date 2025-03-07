package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;

import java.util.List;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private EmployeeRepository repository;private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Save Employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        Employee savedEmployee = repository.save(employee);
        return new EmployeeDTO(savedEmployee.getName(), savedEmployee.getSalary());
    }

    // Get all employees as DTO list
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public EmployeeDTO getEmployeeById(Long id) {
        return repository.findById(id)
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .orElse(null);
    }

    // Update Employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        return repository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(updatedEmployeeDTO.getName());
            existingEmployee.setSalary(updatedEmployeeDTO.getSalary());
            repository.save(existingEmployee);
            return new EmployeeDTO(existingEmployee.getName(), existingEmployee.getSalary());
        }).orElse(null);
    }


    // Delete Employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}

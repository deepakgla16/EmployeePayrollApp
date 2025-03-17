package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.interfaces.EmployeeInterface;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeInterface {

    @Autowired
    private EmployeeRepository employeeRepository;


    EmployeeDTO empDTO;
    public Employee addEmployee(EmployeeDTO empDTO){

        Employee emp=new Employee();
            emp.setName(empDTO.getName());
            emp.setSalary(empDTO.getSalary());
            emp.setDepartment(empDTO.getDepartment());
        return employeeRepository.save(emp);

    }
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Empolyee not found"));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();

    }

    public Employee updateEmployee(Long id, EmployeeDTO empDTO){
        Employee found=getEmployeeById(id);
        found.setName(empDTO.getName());
        found.setDepartment(empDTO.getDepartment());
        found.setSalary(empDTO.getSalary());
        return employeeRepository.save(found);

    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}

package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    public Employee addEmployee(@RequestBody EmployeeDTO empDTO){
        log.info("Request for adding:{} ",empDTO);

         Employee employee=employeeService.addEmployee(empDTO);
         log.info("Employee added Successfully with id:{}",employee.getId());
         return  employee;
    }

    @GetMapping("/get")
    private List<Employee> getallEmployees(){
        log.info("Fetching all employees ");

        List<Employee> employees=employeeService.getAllEmployees();
        log.info("Total employees found:{}",employees.size());
        return employees;
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        log.info("Fetching employee with Id:{}", id) ;

        Employee employee=  employeeService.getEmployeeById(id);

        if (employee !=null){
            log.info("Employee found:{}",employee);

        }
        else{
            log.warn("Employee with Id {} not found",id);
        }
        return  employee;

    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO empDTO){
        log.info("request to update employee with id :{}",id);
        Employee employee=employeeService.updateEmployee(id,empDTO);
        log.info("Update successfully");
        return employee;
    }

    @DeleteMapping("/delete/{id}")

    public  void deleteEmployee(@PathVariable Long id){
        log.info("request for delete with id:{}",id);
        employeeService.deleteEmployee(id);
        log.info("Employee with id {} deleted successfully",id);
    }

}

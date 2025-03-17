

package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.Exception.ResourceNotFoundException;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO empDTO){
        try{
            log.info("Request for adding:{} ",empDTO);

            Employee employee=employeeService.addEmployee(empDTO);
            log.info("Employee added Successfully with id:{}",employee.getId());
            return  new ResponseEntity<>(employee, HttpStatus.CREATED);
        }catch (Exception e){
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in adding");
        }
    }

    @GetMapping("/get")
    private ResponseEntity<?> getallEmployees(){
        try {
            log.info("Fetching all employees ");

            List<Employee> employees=employeeService.getAllEmployees();
            log.info("Total employees found:{}",employees.size());
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error fetching employees: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching employees.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try{
            log.info("Fetching employee with Id:{}", id) ;

            Employee employee=  employeeService.getEmployeeById(id);

            if (employee !=null){
                log.info("Employee found:{}",employee);
                return  new ResponseEntity<>(employee,HttpStatus.OK);

            }
            else{
                log.warn("Employee with Id {} not found",id);
                throw new ResourceNotFoundException("Employee Not found with Id"+id);
            }

        }
        catch (Exception e) {
            log.error("Error fetching employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching employee.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO empDTO){
        try{
            log.info("request to update employee with id :{}",id);
            Employee employee=employeeService.updateEmployee(id,empDTO);
            log.info("Update successfully");
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error updating employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee.");
        }
    }

    @DeleteMapping("/delete/{id}")

    public  ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        try {
            log.info("request for delete with id:{}",id);
            employeeService.deleteEmployee(id);
            log.info("Employee with id {} deleted successfully",id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error deleting employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee.");
        }
    }

}

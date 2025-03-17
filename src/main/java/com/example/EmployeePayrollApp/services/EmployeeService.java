
package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.Exception.ResourceNotFoundException;
import com.example.EmployeePayrollApp.interfaces.EmployeeInterface;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j

@Service
public class EmployeeService implements EmployeeInterface {

    @Autowired
    private EmployeeRepository employeeRepository;


    EmployeeDTO empDTO;
    public Employee addEmployee(EmployeeDTO empDTO){
        try {

            Employee emp=new Employee();
            emp.setName(empDTO.getName());
            emp.setSalary(empDTO.getSalary());
            emp.setDepartment(empDTO.getDepartment());
            return employeeRepository.save(emp);

        }catch (Exception e){
            log.error("error adding employee:{} ",e.getMessage());
            throw  new RuntimeException("error in adding employee");
        }
    }
    public Employee getEmployeeById(Long id){

        try {
            Optional<Employee> employee =employeeRepository.findById(id);
            if (employee.isPresent()){
                log.info("Employee found with ID: {}", id);
                return employee.get();
            }else {
                log.warn("Employee not found with ID: {}", id);
                throw  new ResourceNotFoundException("Employe not found with id:"+id);
            }
        }catch (Exception e){
            log.error("Error retrieving employee with ID: {}", id, e);
            throw new RuntimeException("Something went wrong while finding detail");
        }
    }

    public List<Employee> getAllEmployees(){
        try{
            List<Employee> employees=employeeRepository.findAll();
            log.info("Total employees fetched: {}", employees.size());
            return employees;
        }catch (Exception e){
            log.error("Error fetching employees: {}", e.getMessage(), e);
            throw new RuntimeException("Something went wrong while fetching employees.");
        }
    }

    public Employee updateEmployee(Long id, EmployeeDTO empDTO){
        try {
            Employee found=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee id not found"));
            found.setName(empDTO.getName());
            found.setDepartment(empDTO.getDepartment());
            found.setSalary(empDTO.getSalary());


            Employee updatedEmployee= employeeRepository.save(found);
            log.info("Employee updated successfully with ID: {}", id);
            return updatedEmployee;

        }catch (ResourceNotFoundException e){
            log.warn("Update failed:{} ",e.getMessage());
            throw  e;
        }catch (Exception e){
            log.error("Error updating employee with ID: {}", id, e);
            throw new RuntimeException("Something went wrong while updating employee details.");

        }


    }

    @Transactional
    public void deleteEmployee(Long id){try {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        employeeRepository.delete(employee);
        log.info("Employee deleted successfully with ID: {}", id);
    } catch (ResourceNotFoundException e) {
        log.warn("Deletion failed: {}", e.getMessage());
        throw e;
    } catch (Exception e) {
        log.error("Error deleting employee with ID: {}", id, e);
        throw new RuntimeException("Something went wrong while deleting employee.");
    }
    }
}




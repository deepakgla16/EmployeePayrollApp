
package com.example.EmployeePayrollApp.Exception;

public  class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
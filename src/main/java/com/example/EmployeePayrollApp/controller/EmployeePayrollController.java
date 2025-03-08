package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.service.EmployeePayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    EmployeePayrollService employeePayrollService;

    @GetMapping("/get")
    public ResponseEntity<?> getEmployeePayrollData() {
        try {
            List<EmployeePayroll> employeeList = employeePayrollService.getAllEmployees();
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve employee data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<?> getEmployeePayrollData(@PathVariable("empId") int empId) {
        try {
            EmployeePayroll employee = employeePayrollService.getEmployeeById(empId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Employee Not Found with ID: " + empId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        try {
            EmployeePayroll newEmployee = employeePayrollService.addEmployee(empPayrollDTO);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateEmployeePayrollData(
            @PathVariable("empId") int empId,
            @Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        try {
            EmployeePayroll updatedEmployee = employeePayrollService.updateEmployee(empId, empPayrollDTO);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update employee with ID: " + empId, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<?> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        try {
            employeePayrollService.deleteEmployee(empId);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete employee with ID: " + empId, HttpStatus.NOT_FOUND);
        }
    }
}
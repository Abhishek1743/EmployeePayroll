package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<List<EmployeePayroll>> getEmployeePayrollData() {
        List<EmployeePayroll> employeeList = employeePayrollService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<EmployeePayroll> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayroll employee = employeePayrollService.getEmployeeById(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeePayroll> addEmployeePayrollData(@RequestBody EmployeePayrollDTO empPayrollDTO) {
        EmployeePayroll newEmployee = employeePayrollService.addEmployee(empPayrollDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<EmployeePayroll> updateEmployeePayrollData(
            @PathVariable("empId") int empId, @RequestBody EmployeePayrollDTO empPayrollDTO) {
        EmployeePayroll updatedEmployee = employeePayrollService.updateEmployee(empId, empPayrollDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        employeePayrollService.deleteEmployee(empId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}

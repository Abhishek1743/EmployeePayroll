package com.example.EmployeePayrollApp.controller;


import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollRepository repository;

    @GetMapping("/get")
    public List<EmployeePayroll> getEmployeePayrollData() {
        return repository.findAll();
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<EmployeePayroll> getEmployeePayrollData(@PathVariable("empId") int empId) {
        return repository.findById(empId)
                .map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public EmployeePayroll addEmployeePayrollData(@RequestBody EmployeePayroll empPayroll) {
        return repository.save(empPayroll);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<EmployeePayroll> updateEmployeePayrollData(@PathVariable int empId, @RequestBody EmployeePayroll empDetails) {
        return repository.findById(empId)
                .map(emp -> {
                    emp.setName(empDetails.getName());
                    emp.setSalary(empDetails.getSalary());
                    return new ResponseEntity<>(repository.save(emp), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable int empId) {
        if (repository.existsById(empId)) {
            repository.deleteById(empId);
            return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
        }
    }
}

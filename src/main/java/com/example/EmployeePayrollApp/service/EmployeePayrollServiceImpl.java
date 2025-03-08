package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService {

    @Autowired
    EmployeePayrollRepository repository;

    @Override
    public List<EmployeePayroll> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public EmployeePayroll getEmployeeById(int empId) {
        return repository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
    }

    @Override
    public EmployeePayroll addEmployee(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayroll newEmployee = new EmployeePayroll(
                empPayrollDTO.getName(),
                empPayrollDTO.getSalary(),
                empPayrollDTO.getDepartment()
        );
        return repository.save(newEmployee);
    }

    @Override
    public EmployeePayroll updateEmployee(int empId, EmployeePayrollDTO empPayrollDTO) {
        EmployeePayroll employee = repository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        employee.setName(empPayrollDTO.getName());
        employee.setSalary(empPayrollDTO.getSalary());
        employee.setDepartment(empPayrollDTO.getDepartment());

        return repository.save(employee);
    }

    @Override
    public void deleteEmployee(int empId) {
        if (!repository.existsById(empId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found");
        }
        repository.deleteById(empId);
    }
}
package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository repository;

    @Override
    public List<EmployeePayroll> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public EmployeePayroll getEmployeeById(int empId) {
        return repository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    @Override
    public EmployeePayroll addEmployee(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayroll newEmployee = new EmployeePayroll(empPayrollDTO.getName(), empPayrollDTO.getSalary());
        return repository.save(newEmployee);
    }

    @Override
    public EmployeePayroll updateEmployee(int empId, EmployeePayrollDTO empPayrollDTO) {
        Optional<EmployeePayroll> existingEmployee = repository.findById(empId);
        if (existingEmployee.isPresent()) {
            EmployeePayroll employee = existingEmployee.get();
            employee.setName(empPayrollDTO.getName());
            employee.setSalary(empPayrollDTO.getSalary());
            return repository.save(employee);
        } else {
            throw new RuntimeException("Employee Not Found");
        }
    }

    @Override
    public void deleteEmployee(int empId) {
        repository.deleteById(empId);
    }
}

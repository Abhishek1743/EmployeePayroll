package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import com.example.EmployeePayrollApp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService {

    @Autowired
    EmployeePayrollRepository employeePayrollRepository;

    @Override
    public List<EmployeePayroll> getAllEmployees() {
        return employeePayrollRepository.findAll();
    }

    @Override
    public EmployeePayroll getEmployeeById(int empId) {
        return employeePayrollRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));
    }

    @Override
    public EmployeePayroll addEmployee(EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayroll employee = new EmployeePayroll();
        employee.setName(employeePayrollDTO.getName());
        employee.setSalary(employeePayrollDTO.getSalary());
        employee.setDepartment(employeePayrollDTO.getDepartment()); // Single department

        return employeePayrollRepository.save(employee);
    }

    @Override
    public EmployeePayroll updateEmployee(int empId, EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayroll existingEmployee = getEmployeeById(empId);
        existingEmployee.setName(employeePayrollDTO.getName());
        existingEmployee.setSalary(employeePayrollDTO.getSalary());
        existingEmployee.setDepartment(employeePayrollDTO.getDepartment()); // Update single department

        return employeePayrollRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int empId) {
        employeePayrollRepository.deleteById(empId);
    }
}
package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeePayrollDTO;
import com.example.EmployeePayrollApp.model.EmployeePayroll;
import java.util.List;

public interface EmployeePayrollService {
    List<EmployeePayroll> getAllEmployees();
    EmployeePayroll getEmployeeById(int empId);
    EmployeePayroll addEmployee(EmployeePayrollDTO empPayrollDTO);
    EmployeePayroll updateEmployee(int empId, EmployeePayrollDTO empPayrollDTO);
    void deleteEmployee(int empId);
}

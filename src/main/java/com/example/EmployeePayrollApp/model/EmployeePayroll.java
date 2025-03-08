package com.example.EmployeePayrollApp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employee_payroll")
public class EmployeePayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long salary;
    private String department;

    // Constructors
    public EmployeePayroll() {}

    public EmployeePayroll(String name, long salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

}

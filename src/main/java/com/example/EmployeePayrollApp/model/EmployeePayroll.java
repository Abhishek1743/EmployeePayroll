package com.example.EmployeePayrollApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    // Constructors
    public EmployeePayroll() {}

    public EmployeePayroll(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

}

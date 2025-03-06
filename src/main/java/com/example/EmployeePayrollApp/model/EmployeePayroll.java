package com.example.EmployeePayrollApp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "employee_payroll")
public class EmployeePayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long salary;

    // Constructors, Getters, Setters
    public EmployeePayroll() {}

    public EmployeePayroll(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getSalary() { return salary; }
    public void setSalary(long salary) { this.salary = salary; }
}

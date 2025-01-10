package com.example.erp.Model;

import com.example.erp.Model.Store;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String name;
    private String role;
    private Double salary;
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // Many employees belong to one store

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


    // Constructors
    public Employee() {}

    public Employee(Integer employeeId, String name, String role, Double salary, LocalDate hireDate, Store store) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.hireDate = hireDate;
        this.store = store;
    }

    // Getters and Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

package com.example.erp.DTO;

import java.time.LocalDate;

public class EmployeeDTO {
    private Integer employeeId;
    private String name;
    private String role;
    private Double salary;
    private LocalDate hireDate;
    private String departmentName; // From linked Department
    private String storeName; // From linked Store

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(Integer employeeId, String name, String role, Double salary, LocalDate hireDate, String departmentName, String storeName) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.hireDate = hireDate;
        this.departmentName = departmentName;
        this.storeName = storeName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

package com.example.erp.DTO;

public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private Double budget;

    // Constructors
    public DepartmentDTO() {}

    public DepartmentDTO(Integer departmentId, String departmentName, Double budget) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.budget = budget;
    }

    // Getters and Setters
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}

package com.example.erp.Service;

import com.example.erp.Model.Department;
import com.example.erp.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
    }

    // Create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Update an existing department
    public Department updateDepartment(Department existingDepartment, Department departmentDetails) {
        existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
        existingDepartment.setBudget(departmentDetails.getBudget());
        return departmentRepository.save(existingDepartment);
    }

    // Delete a department
    public void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }
}

package com.example.erp.controller;

import com.example.erp.Model.Department;
import com.example.erp.Repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setBudget(departmentDetails.getBudget());
        return departmentRepository.save(department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        departmentRepository.delete(department);
        return "Department with id " + id + " has been deleted.";
    }
}

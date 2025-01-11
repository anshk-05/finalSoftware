package com.example.erp.Controller;

import com.example.erp.DTO.DepartmentDTO;
import com.example.erp.Model.Department;
import com.example.erp.Service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Get all departments
    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments().stream()
                .map(department -> new DepartmentDTO(
                        department.getDepartmentId(),
                        department.getDepartmentName(),
                        department.getBudget()
                ))
                .collect(Collectors.toList());
    }

    // Get a single department
    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable Integer id) {
        Department department = departmentService.getDepartmentById(id);
        return new DepartmentDTO(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getBudget()
        );
    }

    // Create a new department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    // Update a department
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department departmentDetails) {
        Department existingDepartment = departmentService.getDepartmentById(id);
        return departmentService.updateDepartment(existingDepartment, departmentDetails);
    }

    // Delete a department
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        Department department = departmentService.getDepartmentById(id);
        departmentService.deleteDepartment(department);
        return "Department with id " + id + " has been deleted.";
    }
}

package com.example.erp.Controller;

import com.example.erp.DTO.EmployeeDTO;
import com.example.erp.Model.Employee;
import com.example.erp.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getRole().getRoleName(), // Use Role entity
                        employee.getSalary(),
                        employee.getHireDate(),
                        employee.getDepartment().getDepartmentName(),
                        employee.getStore().getStoreName()
                ))
                .collect(Collectors.toList());
    }

    // Get a single employee
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getRole().getRoleName(),
                employee.getSalary(),
                employee.getHireDate(),
                employee.getDepartment().getDepartmentName(),
                employee.getStore().getStoreName()
        );
    }

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Update an employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " has been deleted.";
    }
}

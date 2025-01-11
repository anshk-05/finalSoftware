package com.example.erp.Controller;

import com.example.erp.DTO.EmployeeDTO;
import com.example.erp.Model.Employee;
import com.example.erp.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
                        employee.getRole(),
                        employee.getSalary(),
                        employee.getHireDate(), // No .toString(), use LocalDate directly
                        employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : null,
                        employee.getStore() != null ? employee.getStore().getStoreName() : null
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
                employee.getRole(),
                employee.getSalary(),
                employee.getHireDate(), // Use LocalDate directly
                employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : null,
                employee.getStore() != null ? employee.getStore().getStoreName() : null
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
        Employee existingEmployee = employeeService.getEmployeeById(id);
        return employeeService.updateEmployee(existingEmployee, employeeDetails);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return "Employee with id " + id + " has been deleted.";
    }
}

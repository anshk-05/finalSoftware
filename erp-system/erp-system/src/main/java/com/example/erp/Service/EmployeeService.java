package com.example.erp.Service;

import com.example.erp.Model.Employee;
import com.example.erp.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(Employee existingEmployee, Employee employeeDetails) {
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setHireDate(employeeDetails.getHireDate());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        existingEmployee.setStore(employeeDetails.getStore());
        return employeeRepository.save(existingEmployee);
    }

    // Delete an employee
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}

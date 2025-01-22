package com.example.erp.Service;

import com.example.erp.Model.Department;
import com.example.erp.Model.Employee;
import com.example.erp.Model.Role;
import com.example.erp.Model.Store;
import com.example.erp.Repository.EmployeeRepository;
import com.example.erp.Repository.RoleRepository;
import com.example.erp.Repository.DepartmentRepository;
import com.example.erp.Repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final StoreRepository storeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, StoreRepository storeRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.storeRepository = storeRepository;
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

    @Transactional
    public Employee createEmployee(Employee employeeDetails) {
        // Fetch existing Department
        Department department = departmentRepository.findById(employeeDetails.getDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id " + employeeDetails.getDepartment().getDepartmentId()));

        // Fetch existing Store
        Store store = storeRepository.findById(employeeDetails.getStore().getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found with id " + employeeDetails.getStore().getStoreId()));

        // Fetch existing Role
        Role role = roleRepository.findById(employeeDetails.getRole().getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id " + employeeDetails.getRole().getRoleId()));

        // Set fetched references
        employeeDetails.setDepartment(department);
        employeeDetails.setStore(store);
        employeeDetails.setRole(role);

        return employeeRepository.save(employeeDetails);
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        // Fetch the existing employee
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        // Fetch existing Department
        Department department = departmentRepository.findById(employeeDetails.getDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id " + employeeDetails.getDepartment().getDepartmentId()));

        // Fetch existing Store
        Store store = storeRepository.findById(employeeDetails.getStore().getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found with id " + employeeDetails.getStore().getStoreId()));

        // Fetch existing Role
        Role role = roleRepository.findById(employeeDetails.getRole().getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id " + employeeDetails.getRole().getRoleId()));

        // Update details
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setHireDate(employeeDetails.getHireDate());
        existingEmployee.setDepartment(department); // Set existing Department
        existingEmployee.setStore(store);           // Set existing Store
        existingEmployee.setRole(role);             // Set existing Role

        return employeeRepository.save(existingEmployee);
    }




    // Delete an employee
    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}

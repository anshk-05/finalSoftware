package com.example.erp.Controller;

import com.example.erp.Model.Employee;
import com.example.erp.Repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeRepository employeeRepository;

    public AuthController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Employee employee = employeeRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check password
        if (!password.equals(employee.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Mock token and role response
        Map<String, Object> response = new HashMap<>();
        response.put("token", "mock-jwt-token");
        response.put("role", employee.getRole().getRoleName());

        return ResponseEntity.ok(response);
    }

}

package com.example.erp.Controller;

import com.example.erp.DTO.RoleDTO;
import com.example.erp.Model.Role;
import com.example.erp.Service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Get all roles
    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    // Get a single role by ID
    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    // Create a new role
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Update an existing role
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Integer id, @RequestBody Role roleDetails) {
        return roleService.updateRole(id, roleDetails);
    }

    // Delete a role
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return "Role with id " + id + " has been deleted.";
    }
}

package com.example.erp.Service;

import com.example.erp.DTO.RoleDTO;
import com.example.erp.Model.Role;
import com.example.erp.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get all roles
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDTO(role.getRoleId(), role.getRoleName(), role.getDescription()))
                .collect(Collectors.toList());
    }

    // Get a role by ID
    public RoleDTO getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        return new RoleDTO(role.getRoleId(), role.getRoleName(), role.getDescription());
    }

    // Get a role by name
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name " + roleName));
    }

    // Create a new role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Update an existing role
    public Role updateRole(Integer id, Role roleDetails) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        role.setRoleName(roleDetails.getRoleName());
        role.setDescription(roleDetails.getDescription());
        return roleRepository.save(role);
    }

    // Delete a role
    public void deleteRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        roleRepository.delete(role);
    }
}

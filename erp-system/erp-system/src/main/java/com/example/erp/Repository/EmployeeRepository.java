package com.example.erp.Repository;

import com.example.erp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);

    @Query("SELECT SUM(e.salary) FROM Employee e")
    Double getTotalSalaries();

}


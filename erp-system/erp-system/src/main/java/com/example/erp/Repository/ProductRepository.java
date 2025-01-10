package com.example.erp.Repository;

import com.example.erp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // JpaRepository provides built-in CRUD operations
}


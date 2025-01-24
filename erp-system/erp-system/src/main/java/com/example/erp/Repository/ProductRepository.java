package com.example.erp.Repository;

import com.example.erp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Find products below the reorder level
    @Query("SELECT p FROM Product p WHERE p.stockLevel <= p.reorderLevel")
    List<Product> findProductsBelowReorderLevel();

    // Find products by supplier
    List<Product> findBySupplier_SupplierId(Integer supplierId);

    // Find products by category
    List<Product> findByCategory(String category);
}

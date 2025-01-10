package com.example.erp.Model;

import jakarta.persistence.*;
import com.example.erp.Model.Store;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database auto-increment
    private Integer productId;


    private String productName;
    private String category;
    private Double price;
    private Integer stockLevel;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // Many products belong to one store

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true) // Product may or may not have a supplier
    private Supplier supplier;

    // Constructors
    public Product() {}

    public Product(Integer productId, String productName, String category, Double price, Integer stockLevel, Store store) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockLevel = stockLevel;
        this.store = store;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}

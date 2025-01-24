package com.example.erp.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use database auto-increment
    private Integer productId;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(length = 50)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockLevel;

    @Column(nullable = true)
    private Integer reorderLevel;

    @Column
    private LocalDate lastPurchaseDate;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = true) // Store is optional
    private Store store;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true) // Supplier is optional
    private Supplier supplier;

    // Constructors
    public Product() {}

    public Product(Integer productId, String productName, String category, Double price, Integer stockLevel, Integer reorderLevel, LocalDate lastPurchaseDate, Store store, Supplier supplier) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockLevel = stockLevel;
        this.reorderLevel = reorderLevel;
        this.lastPurchaseDate = lastPurchaseDate;
        this.store = store;
        this.supplier = supplier;
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

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public LocalDate getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
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

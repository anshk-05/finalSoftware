package com.example.erp.DTO;

import java.time.LocalDate;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String category;
    private Double price;
    private Integer stockLevel;
    private Integer reorderLevel; // New field for reorder level
    private LocalDate lastPurchaseDate; // New field for last purchase date
    private String storeName; // Store name
    private String supplierName; // Supplier name

    // Default Constructor
    public ProductDTO() {}

    // Constructor
    public ProductDTO(Integer productId, String productName, String category, Double price, Integer stockLevel,
                      Integer reorderLevel, LocalDate lastPurchaseDate, String storeName, String supplierName) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockLevel = stockLevel;
        this.reorderLevel = reorderLevel;
        this.lastPurchaseDate = lastPurchaseDate;
        this.storeName = storeName;
        this.supplierName = supplierName;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}

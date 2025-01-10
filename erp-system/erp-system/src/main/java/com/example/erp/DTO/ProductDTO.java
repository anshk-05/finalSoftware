package com.example.erp.DTO;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String category;
    private Double price;
    private Integer stockLevel;
    private String storeName; // From the linked Store
    private String supplierName; // From the linked Supplier

    // Constructors
    public ProductDTO() {}

    public ProductDTO(Integer productId, String productName, String category, Double price, Integer stockLevel, String storeName, String supplierName) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockLevel = stockLevel;
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

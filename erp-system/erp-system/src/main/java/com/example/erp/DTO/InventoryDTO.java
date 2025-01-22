package com.example.erp.DTO;

public class InventoryDTO {
    private Integer inventoryId;
    private String productName;
    private Integer stockLevel;
    private Double price;
    private String category;

    // Constructor
    public InventoryDTO(Integer inventoryId, String productName, Integer stockLevel, Double price, String category) {
        this.inventoryId = inventoryId;
        this.productName = productName;
        this.stockLevel = stockLevel;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

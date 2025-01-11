package com.example.erp.DTO;

public class InventoryDTO {
    private Integer inventoryId;
    private Integer productId;
    private String productName;
    private Integer storeId;
    private String storeName;
    private Integer stockLevel;
    private Integer reorderThreshold;

    // Constructors
    public InventoryDTO() {}

    public InventoryDTO(Integer inventoryId, Integer productId, String productName, Integer storeId, String storeName, Integer stockLevel, Integer reorderThreshold) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.productName = productName;
        this.storeId = storeId;
        this.storeName = storeName;
        this.stockLevel = stockLevel;
        this.reorderThreshold = reorderThreshold;
    }

    // Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Integer getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(Integer reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }
}

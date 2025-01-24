package com.example.erp.DTO;

public class PurchaseOrderProductDTO {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;

    // Default Constructor
    public PurchaseOrderProductDTO() {}

    // Parameterized Constructor
    public PurchaseOrderProductDTO(Integer productId, String productName, Integer quantity, Double pricePerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}

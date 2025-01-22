package com.example.erp.DTO;

public class SalesProductDTO {
    private Integer productId; // ID of the product
    private String productName; // Name of the product
    private Integer quantity; // Quantity sold
    private Double pricePerUnit; // Price per unit of the product

    // Default Constructor
    public SalesProductDTO() {}

    // Parameterized Constructor
    public SalesProductDTO(Integer productId, String productName, Integer quantity, Double pricePerUnit) {
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

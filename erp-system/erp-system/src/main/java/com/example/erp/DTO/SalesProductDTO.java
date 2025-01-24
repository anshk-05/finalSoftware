package com.example.erp.DTO;

public class SalesProductDTO {
    private Integer productId; // ID of the product
    private Integer salesId;   // ID of the sales (optional for the backend to set later)
    private Integer quantity;  // Quantity sold
    private Double pricePerUnit; // Price per unit of the product

    // Default Constructor
    public SalesProductDTO() {}

    // Parameterized Constructor
    public SalesProductDTO(Integer productId, Integer salesId, Integer quantity, Double pricePerUnit) {
        this.productId = productId;
        this.salesId = salesId;
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

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
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

package com.example.erp.DTO;

import java.time.LocalDate;

public class SalesDTO {
    private Integer salesId; // Sales ID
    private LocalDate dateOfSale; // Date of the sale
    private Double totalAmount; // Total amount for the sale
    private String paymentMethod; // Payment method (e.g., Credit Card, Cash)
    private String storeName; // Name of the store where the sale occurred
    private String employeeName; // Name of the employee responsible for the sale
    private Integer productId; // ID of the product sold
    private String productName; // Name of the product sold
    private Integer quantity; // Quantity sold
    private Double pricePerUnit; // Price per unit of the product sold

    // Default Constructor
    public SalesDTO() {}

    // Parameterized Constructor
    public SalesDTO(
            Integer salesId,
            LocalDate dateOfSale,
            Double totalAmount,
            String paymentMethod,
            String storeName,
            String employeeName,
            Integer productId,
            String productName,
            Integer quantity,
            Double pricePerUnit
    ) {
        this.salesId = salesId;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.storeName = storeName;
        this.employeeName = employeeName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    // Getters and Setters
    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

package com.example.erp.DTO;

public class SalesDTO {
    private Integer salesId;
    private String dateOfSale;
    private Double totalAmount;
    private String paymentMethod;
    private String storeName; // From linked Store
    private String employeeName; // From linked Employee

    // Constructors
    public SalesDTO() {}

    public SalesDTO(Integer salesId, String dateOfSale, Double totalAmount, String paymentMethod, String storeName, String employeeName) {
        this.salesId = salesId;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.storeName = storeName;
        this.employeeName = employeeName;
    }

    // Getters and Setters
    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
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
}

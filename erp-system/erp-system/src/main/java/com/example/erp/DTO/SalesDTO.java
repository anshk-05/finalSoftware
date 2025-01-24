package com.example.erp.DTO;

import java.time.LocalDate;
import java.util.List;

public class SalesDTO {
    private Integer salesId; // Sales ID
    private LocalDate dateOfSale; // Date of the sale
    private Double totalAmount; // Total amount for the sale
    private String paymentMethod; // Payment method (e.g., Credit Card, Cash)
    private Integer storeId; // ID of the store (used for service methods)
    private String storeName; // Name of the store (used for repository query)
    private Integer employeeId; // ID of the employee (used for service methods)
    private String employeeName; // Name of the employee (used for repository query)
    private List<SalesProductDTO> products; // List of products sold in this sale

    // Default Constructor
    public SalesDTO() {}

    // Constructor for repository query (detailed data)
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
        this.products = List.of(new SalesProductDTO(productId, salesId, quantity, pricePerUnit));
    }

    // Constructor for service methods (minimal data)
    public SalesDTO(
            Integer salesId,
            LocalDate dateOfSale,
            Double totalAmount,
            String paymentMethod,
            Integer storeId,
            Integer employeeId,
            List<SalesProductDTO> products
    ) {
        this.salesId = salesId;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.storeId = storeId;
        this.employeeId = employeeId;
        this.products = products;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<SalesProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SalesProductDTO> products) {
        this.products = products;
    }
}

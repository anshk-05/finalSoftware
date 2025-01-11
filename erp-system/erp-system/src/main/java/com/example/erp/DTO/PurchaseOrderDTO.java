package com.example.erp.DTO;

import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderDTO {
    private Integer purchaseOrderId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Double totalAmount;
    private String supplierName;
    private List<String> productNames;

    // Constructors
    public PurchaseOrderDTO() {}

    public PurchaseOrderDTO(Integer purchaseOrderId, LocalDate orderDate, LocalDate deliveryDate, Double totalAmount, String supplierName, List<String> productNames) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
        this.supplierName = supplierName;
        this.productNames = productNames;
    }

    // Getters and Setters
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }
}

package com.example.erp.DTO;

import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderDTO {
    private Integer purchaseOrderId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Double totalAmount;
    private String orderStatus; // New field for the order status
    private String supplierName; // Supplier name
    private List<PurchaseOrderProductDTO> products; // List of associated products

    // Default Constructor
    public PurchaseOrderDTO() {}

    // Constructor
    public PurchaseOrderDTO(Integer purchaseOrderId, LocalDate orderDate, LocalDate deliveryDate, Double totalAmount,
                            String orderStatus, String supplierName, List<PurchaseOrderProductDTO> products) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.supplierName = supplierName;
        this.products = products;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<PurchaseOrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<PurchaseOrderProductDTO> products) {
        this.products = products;
    }
}

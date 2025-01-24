package com.example.erp.Model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PurchaseOrderProduct implements Serializable {

    @EmbeddedId
    private PurchaseOrderProductId id;

    @ManyToOne
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double pricePerUnit;

    // Default Constructor
    public PurchaseOrderProduct() {}

    // Parameterized Constructor
    public PurchaseOrderProduct(PurchaseOrder purchaseOrder, Product product, Integer quantity, Double pricePerUnit) {
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.id = new PurchaseOrderProductId(purchaseOrder.getPurchaseOrderId(), product.getProductId());
    }

    // Getters and Setters
    public PurchaseOrderProductId getId() {
        return id;
    }

    public void setId(PurchaseOrderProductId id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

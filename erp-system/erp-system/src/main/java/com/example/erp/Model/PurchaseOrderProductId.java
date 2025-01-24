package com.example.erp.Model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseOrderProductId implements Serializable {

    private Integer purchaseOrderId;
    private Integer productId;

    // Default constructor
    public PurchaseOrderProductId() {}

    // Parameterized constructor
    public PurchaseOrderProductId(Integer purchaseOrderId, Integer productId) {
        this.purchaseOrderId = purchaseOrderId;
        this.productId = productId;
    }

    // Getters and setters
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderProductId that = (PurchaseOrderProductId) o;
        return Objects.equals(purchaseOrderId, that.purchaseOrderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseOrderId, productId);
    }
}

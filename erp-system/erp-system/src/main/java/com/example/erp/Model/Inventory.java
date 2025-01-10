package com.example.erp.Model;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Inventory is linked to a product

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store; // Inventory may be linked to a store

    private Integer stockLevel;
    private Integer reorderThreshold;

    // Constructors
    public Inventory() {}

    public Inventory(Integer inventoryId, Product product, Store store, Integer stockLevel, Integer reorderThreshold) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.store = store;
        this.stockLevel = stockLevel;
        this.reorderThreshold = reorderThreshold;
    }

    // Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Integer getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(Integer reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }
}

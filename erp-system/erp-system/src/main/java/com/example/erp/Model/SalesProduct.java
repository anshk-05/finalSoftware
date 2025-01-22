package com.example.erp.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "sales_products")
public class SalesProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesProductId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_id", nullable = false)
    private Sales sales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double pricePerUnit;

    // Constructors, Getters, and Setters
    public SalesProduct() {}

    public SalesProduct(Integer salesProductId, Sales sales, Product product, Integer quantity, Double pricePerUnit) {
        this.salesProductId = salesProductId;
        this.sales = sales;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getSalesProductId() { return salesProductId; }
    public void setSalesProductId(Integer salesProductId) { this.salesProductId = salesProductId; }

    public Sales getSales() { return sales; }
    public void setSales(Sales sales) { this.sales = sales; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(Double pricePerUnit) { this.pricePerUnit = pricePerUnit; }
}

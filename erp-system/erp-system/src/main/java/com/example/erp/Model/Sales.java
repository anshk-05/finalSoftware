package com.example.erp.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.erp.Model.Store;
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;

    private LocalDate dateOfSale;
    private Double totalAmount;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // Each sale is linked to a store

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Each sale is linked to an employee

    // Constructors
    public Sales() {}

    public Sales(Integer salesId, LocalDate dateOfSale, Double totalAmount, String paymentMethod, Store store, Employee employee) {
        this.salesId = salesId;
        this.dateOfSale = dateOfSale;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.store = store;
        this.employee = employee;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

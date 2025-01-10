package com.example.erp.DTO;

public class StoreDTO {
    private Integer storeId;
    private String storeName;
    private String location;
    private Integer managerId;
    private Double totalSales;

    private String operatingHours;
    private String contactNumber;

    // Constructors
    public StoreDTO() {}

    public StoreDTO(Integer storeId, String storeName, String location, Integer managerId, Double totalSales, String operatingHours, String contactNumber) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.managerId = managerId;
        this.totalSales = totalSales;
        this.operatingHours = operatingHours;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

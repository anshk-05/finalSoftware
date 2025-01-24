package com.example.erp.DTO;

public class SupplierDTO {
    private Integer supplierId;
    private String supplierName;
    private String contactDetails;
    private String location;
    private String contractTerms;

    // Default Constructor
    public SupplierDTO() {}

    // Parameterized Constructor
    public SupplierDTO(Integer supplierId, String supplierName, String contactDetails, String location, String contractTerms) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactDetails = contactDetails;
        this.location = location;
        this.contractTerms = contractTerms;
    }

    // Getters and Setters
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }
}

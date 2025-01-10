package com.example.erp.Service;

import com.example.erp.Model.Supplier;
import com.example.erp.Repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get supplier by ID
    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
    }

    // Create a new supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Update an existing supplier
    public Supplier updateSupplier(Supplier existingSupplier, Supplier supplierDetails) {
        existingSupplier.setSupplierName(supplierDetails.getSupplierName());
        existingSupplier.setContactDetails(supplierDetails.getContactDetails());
        existingSupplier.setLocation(supplierDetails.getLocation());
        existingSupplier.setContractTerms(supplierDetails.getContractTerms());
        return supplierRepository.save(existingSupplier);
    }

    // Delete a supplier
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
}


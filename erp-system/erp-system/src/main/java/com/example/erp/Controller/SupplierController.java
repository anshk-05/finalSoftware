package com.example.erp.Controller;

import com.example.erp.DTO.SupplierDTO;
import com.example.erp.Model.Supplier;
import com.example.erp.Service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Get all suppliers
    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers().stream()
                .map(supplier -> new SupplierDTO(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getContactDetails(),
                        supplier.getLocation(),
                        supplier.getContractTerms()
                ))
                .collect(Collectors.toList());
    }

    // Get a single supplier
    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable Integer id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return new SupplierDTO(
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getContactDetails(),
                supplier.getLocation(),
                supplier.getContractTerms()
        );
    }

    // Create a new supplier
    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    // Update a supplier
    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplierDetails) {
        Supplier existingSupplier = supplierService.getSupplierById(id);
        return supplierService.updateSupplier(existingSupplier, supplierDetails);
    }

    // Delete a supplier
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        Supplier supplier = supplierService.getSupplierById(id);
        supplierService.deleteSupplier(supplier);
        return "Supplier with id " + id + " has been deleted.";
    }
}

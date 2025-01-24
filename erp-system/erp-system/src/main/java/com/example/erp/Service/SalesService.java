package com.example.erp.Service;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.*;
import com.example.erp.Repository.ProductRepository;
import com.example.erp.Repository.SalesRepository;
import com.example.erp.Repository.StoreRepository;
import com.example.erp.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository; // For managing stock levels
    private final PurchaseOrderService purchaseOrderService; // For triggering reorders
    private final StoreRepository storeRepository;
    private final EmployeeRepository employeeRepository;

    public SalesService(SalesRepository salesRepository, ProductRepository productRepository, PurchaseOrderService purchaseOrderService, StoreRepository storeRepository, EmployeeRepository employeeRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.purchaseOrderService = purchaseOrderService;
        this.storeRepository = storeRepository;
        this.employeeRepository = employeeRepository;
    }

    // Get all sales details (no changes needed here)
    public List<SalesDTO> getAllSales() {
        return salesRepository.getSalesWithDetails();
    }

    public Sales createSales(SalesDTO salesDTO) {
        // 1. Set Sales Details
        Sales sale = new Sales();
        sale.setDateOfSale(salesDTO.getDateOfSale());
        sale.setPaymentMethod(salesDTO.getPaymentMethod());

        // Set Store and Employee by their IDs
        Store store = storeRepository.findById(salesDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + salesDTO.getStoreId()));
        sale.setStore(store);

        Employee employee = employeeRepository.findById(salesDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + salesDTO.getEmployeeId()));
        sale.setEmployee(employee);

        // 2. Handle Product Stock Levels and Create SalesProducts
        List<SalesProduct> salesProducts = salesDTO.getProducts().stream().map(productDTO -> {
            Product product = productRepository.findById(productDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productDTO.getProductId()));

            // Reduce stock level
            if (product.getStockLevel() < productDTO.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }
            product.setStockLevel(product.getStockLevel() - productDTO.getQuantity());
            productRepository.save(product);

            // Create SalesProduct
            return new SalesProduct(
                    sale,
                    product,
                    productDTO.getQuantity(),
                    productDTO.getPricePerUnit()
            );
        }).collect(Collectors.toList());

        sale.setSalesProducts(salesProducts);

        // 3. Calculate Total Amount
        Double totalAmount = salesProducts.stream()
                .mapToDouble(sp -> sp.getQuantity() * sp.getPricePerUnit())
                .sum();
        sale.setTotalAmount(totalAmount);

        // 4. Save Sales
        return salesRepository.save(sale);
    }


}

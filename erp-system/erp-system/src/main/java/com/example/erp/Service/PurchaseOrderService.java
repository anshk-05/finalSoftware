package com.example.erp.Service;

import com.example.erp.Model.PurchaseOrder;
import com.example.erp.Model.Supplier;
import com.example.erp.Model.Product;
import com.example.erp.Repository.PurchaseOrderRepository;
import com.example.erp.Service.SupplierService;
import com.example.erp.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierService supplierService;
    private final ProductService productService;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, SupplierService supplierService, ProductService productService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    // Get all purchase orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Get purchase order by ID
    public PurchaseOrder getPurchaseOrderById(Integer id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found with id " + id));
    }

    // Create a new purchase order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        // Ensure the supplier exists
        Supplier supplier = supplierService.getSupplierById(purchaseOrder.getSupplier().getSupplierId());
        purchaseOrder.setSupplier(supplier);

        // Ensure all products exist
        List<Product> products = purchaseOrder.getProducts().stream()
                .map(product -> productService.getProductById(product.getProductId()))
                .collect(Collectors.toList());
        purchaseOrder.setProducts(products);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    // Update an existing purchase order
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder existingOrder, PurchaseOrder orderDetails) {
        existingOrder.setOrderDate(orderDetails.getOrderDate());
        existingOrder.setDeliveryDate(orderDetails.getDeliveryDate());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount());

        // Update linked supplier
        Supplier supplier = supplierService.getSupplierById(orderDetails.getSupplier().getSupplierId());
        existingOrder.setSupplier(supplier);

        // Update linked products
        List<Product> products = orderDetails.getProducts().stream()
                .map(product -> productService.getProductById(product.getProductId()))
                .collect(Collectors.toList());
        existingOrder.setProducts(products);

        return purchaseOrderRepository.save(existingOrder);
    }

    // Delete a purchase order
    public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.delete(purchaseOrder);
    }
}

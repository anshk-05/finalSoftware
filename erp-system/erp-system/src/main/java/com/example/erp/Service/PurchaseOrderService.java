package com.example.erp.Service;

import com.example.erp.DTO.PurchaseOrderDTO;
import com.example.erp.DTO.PurchaseOrderProductDTO;
import com.example.erp.Model.Product;
import com.example.erp.Model.PurchaseOrder;
import com.example.erp.Model.PurchaseOrderProduct;
import com.example.erp.Repository.ProductRepository;
import com.example.erp.Repository.PurchaseOrderRepository;
import com.example.erp.Repository.PurchaseOrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderProductRepository purchaseOrderProductRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                ProductRepository productRepository,
                                PurchaseOrderProductRepository purchaseOrderProductRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.purchaseOrderProductRepository = purchaseOrderProductRepository;
    }

    // Get all purchase orders
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll().stream()
                .map(order -> new PurchaseOrderDTO(
                        order.getPurchaseOrderId(),
                        order.getOrderDate(),
                        order.getDeliveryDate(),
                        order.getTotalAmount(),
                        order.getOrderStatus(),
                        order.getSupplier() != null ? order.getSupplier().getSupplierName() : null,
                        order.getProducts().stream()
                                .map(poProduct -> new PurchaseOrderProductDTO(
                                        poProduct.getProduct().getProductId(),
                                        poProduct.getProduct().getProductName(),
                                        poProduct.getQuantity(),
                                        poProduct.getPricePerUnit()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    // Get a single purchase order by ID
    public PurchaseOrder getPurchaseOrderEntityById(Integer id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase order not found with id " + id));
    }

    // Get purchase order as DTO
    public PurchaseOrderDTO getPurchaseOrderById(Integer id) {
        PurchaseOrder order = getPurchaseOrderEntityById(id);
        return new PurchaseOrderDTO(
                order.getPurchaseOrderId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getSupplier() != null ? order.getSupplier().getSupplierName() : null,
                order.getProducts().stream()
                        .map(poProduct -> new PurchaseOrderProductDTO(
                                poProduct.getProduct().getProductId(),
                                poProduct.getProduct().getProductName(),
                                poProduct.getQuantity(),
                                poProduct.getPricePerUnit()
                        ))
                        .collect(Collectors.toList())
        );
    }

    // Create a new purchase order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        // Save the order itself
        PurchaseOrder savedOrder = purchaseOrderRepository.save(purchaseOrder);

        // Link products to the order
        for (PurchaseOrderProduct poProduct : purchaseOrder.getProducts()) {
            Product product = productRepository.findById(poProduct.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + poProduct.getProduct().getProductId()));

            poProduct.setPurchaseOrder(savedOrder); // Link to the saved order
            poProduct.setProduct(product);          // Link the product entity
            purchaseOrderProductRepository.save(poProduct);
        }

        return savedOrder;
    }

    // Update an existing purchase order
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder existingOrder, PurchaseOrder updatedDetails) {
        existingOrder.setOrderDate(updatedDetails.getOrderDate());
        existingOrder.setDeliveryDate(updatedDetails.getDeliveryDate());
        existingOrder.setTotalAmount(updatedDetails.getTotalAmount());
        existingOrder.setOrderStatus(updatedDetails.getOrderStatus());

        // Clear old product relationships and add updated ones
        purchaseOrderProductRepository.deleteAll(existingOrder.getProducts());
        existingOrder.getProducts().clear();

        for (PurchaseOrderProduct poProduct : updatedDetails.getProducts()) {
            Product product = productRepository.findById(poProduct.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + poProduct.getProduct().getProductId()));

            poProduct.setPurchaseOrder(existingOrder); // Link to the existing order
            poProduct.setProduct(product);            // Link the product entity
            purchaseOrderProductRepository.save(poProduct);
        }

        return purchaseOrderRepository.save(existingOrder);
    }

    // Delete a purchase order
    public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderProductRepository.deleteAll(purchaseOrder.getProducts());
        purchaseOrderRepository.delete(purchaseOrder);
    }
}

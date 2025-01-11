package com.example.erp.Controller;

import com.example.erp.DTO.PurchaseOrderDTO;
import com.example.erp.Model.PurchaseOrder;
import com.example.erp.Service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    // Get all purchase orders
    @GetMapping
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders().stream()
                .map(order -> new PurchaseOrderDTO(
                        order.getPurchaseOrderId(),
                        order.getOrderDate(),
                        order.getDeliveryDate(),
                        order.getTotalAmount(),
                        order.getSupplier().getSupplierName(),
                        order.getProducts().stream().map(product -> product.getProductName()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    // Get a single purchase order
    @GetMapping("/{id}")
    public PurchaseOrderDTO getPurchaseOrderById(@PathVariable Integer id) {
        PurchaseOrder order = purchaseOrderService.getPurchaseOrderById(id);
        return new PurchaseOrderDTO(
                order.getPurchaseOrderId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getTotalAmount(),
                order.getSupplier().getSupplierName(),
                order.getProducts().stream().map(product -> product.getProductName()).collect(Collectors.toList())
        );
    }

    // Create a new purchase order
    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    // Update a purchase order
    @PutMapping("/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Integer id, @RequestBody PurchaseOrder orderDetails) {
        PurchaseOrder existingOrder = purchaseOrderService.getPurchaseOrderById(id);
        return purchaseOrderService.updatePurchaseOrder(existingOrder, orderDetails);
    }

    // Delete a purchase order
    @DeleteMapping("/{id}")
    public String deletePurchaseOrder(@PathVariable Integer id) {
        PurchaseOrder order = purchaseOrderService.getPurchaseOrderById(id);
        purchaseOrderService.deletePurchaseOrder(order);
        return "Purchase Order with id " + id + " has been deleted.";
    }
}

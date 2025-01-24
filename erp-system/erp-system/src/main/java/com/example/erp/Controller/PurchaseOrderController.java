package com.example.erp.Controller;

import com.example.erp.DTO.PurchaseOrderDTO;
import com.example.erp.Model.PurchaseOrder;
import com.example.erp.Service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
        return purchaseOrderService.getAllPurchaseOrders();
    }

    // Get a single purchase order
    @GetMapping("/{id}")
    public PurchaseOrderDTO getPurchaseOrderById(@PathVariable Integer id) {
        return purchaseOrderService.getPurchaseOrderById(id); // Service should return DTO
    }

    // Create a new purchase order
    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    // Update a purchase order
    @PutMapping("/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Integer id, @RequestBody PurchaseOrder orderDetails) {
        PurchaseOrder existingOrder = purchaseOrderService.getPurchaseOrderEntityById(id); // Fetch the entity
        return purchaseOrderService.updatePurchaseOrder(existingOrder, orderDetails);
    }

    // Delete a purchase order
    @DeleteMapping("/{id}")
    public String deletePurchaseOrder(@PathVariable Integer id) {
        PurchaseOrder order = purchaseOrderService.getPurchaseOrderEntityById(id); // Fetch the entity
        purchaseOrderService.deletePurchaseOrder(order);
        return "Purchase order with id " + id + " has been deleted.";
    }
}

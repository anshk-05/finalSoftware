package com.example.erp.Controller;

import com.example.erp.Model.PurchaseOrder;
import com.example.erp.Repository.PurchaseOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @PutMapping("/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Integer id, @RequestBody PurchaseOrder purchaseOrderDetails) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PurchaseOrder not found with id " + id));
        purchaseOrder.setOrderDate(purchaseOrderDetails.getOrderDate());
        purchaseOrder.setDeliveryDate(purchaseOrderDetails.getDeliveryDate());
        purchaseOrder.setTotalAmount(purchaseOrderDetails.getTotalAmount());
        purchaseOrder.setSupplier(purchaseOrderDetails.getSupplier());
        purchaseOrder.setProducts(purchaseOrderDetails.getProducts());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @DeleteMapping("/{id}")
    public String deletePurchaseOrder(@PathVariable Integer id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PurchaseOrder not found with id " + id));
        purchaseOrderRepository.delete(purchaseOrder);
        return "PurchaseOrder with id " + id + " has been deleted.";
    }
}

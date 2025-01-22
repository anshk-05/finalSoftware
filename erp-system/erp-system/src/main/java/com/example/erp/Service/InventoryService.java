package com.example.erp.Service;

import com.example.erp.DTO.InventoryDTO;
import com.example.erp.Model.Inventory;
import com.example.erp.Repository.InventoryRepository;
import com.example.erp.Service.ProductService;
import com.example.erp.Service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductService productService;
    private final StoreService storeService;

    public InventoryService(InventoryRepository inventoryRepository, ProductService productService, StoreService storeService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
        this.storeService = storeService;
    }

    // Get all inventory records (with product details)
    public List<InventoryDTO> getInventoryWithProductDetails() {
        return inventoryRepository.getInventoryWithProductDetails();
    }

    // Get all inventory records
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // Get inventory by ID
    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id " + id));
    }

    // Create a new inventory record
    public Inventory createInventory(Inventory inventory) {
        // Ensure the product exists
        inventory.setProduct(productService.getProductById(inventory.getProduct().getProductId()));

        // Ensure the store exists (if provided)
        if (inventory.getStore() != null) {
            inventory.setStore(storeService.getStoreById(inventory.getStore().getStoreId()));
        }

        return inventoryRepository.save(inventory);
    }

    // Update an existing inventory record
    public Inventory updateInventory(Inventory existingInventory, Inventory inventoryDetails) {
        existingInventory.setStockLevel(inventoryDetails.getStockLevel());
        existingInventory.setReorderThreshold(inventoryDetails.getReorderThreshold());

        // Update linked product
        existingInventory.setProduct(productService.getProductById(inventoryDetails.getProduct().getProductId()));

        // Update linked store (if provided)
        if (inventoryDetails.getStore() != null) {
            existingInventory.setStore(storeService.getStoreById(inventoryDetails.getStore().getStoreId()));
        } else {
            existingInventory.setStore(null); // Remove store linkage if not provided
        }

        return inventoryRepository.save(existingInventory);
    }

    // Delete an inventory record
    public void deleteInventory(Inventory inventory) {
        inventoryRepository.delete(inventory);
    }
}

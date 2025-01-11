package com.example.erp.Controller;

import com.example.erp.DTO.InventoryDTO;
import com.example.erp.Model.Inventory;
import com.example.erp.Service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Get all inventory records
    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory().stream()
                .map(inventory -> new InventoryDTO(
                        inventory.getInventoryId(),
                        inventory.getProduct().getProductId(),
                        inventory.getProduct().getProductName(),
                        inventory.getStore() != null ? inventory.getStore().getStoreId() : null,
                        inventory.getStore() != null ? inventory.getStore().getStoreName() : null,
                        inventory.getStockLevel(),
                        inventory.getReorderThreshold()
                ))
                .collect(Collectors.toList());
    }

    // Get a single inventory record
    @GetMapping("/{id}")
    public InventoryDTO getInventoryById(@PathVariable Integer id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return new InventoryDTO(
                inventory.getInventoryId(),
                inventory.getProduct().getProductId(),
                inventory.getProduct().getProductName(),
                inventory.getStore() != null ? inventory.getStore().getStoreId() : null,
                inventory.getStore() != null ? inventory.getStore().getStoreName() : null,
                inventory.getStockLevel(),
                inventory.getReorderThreshold()
        );
    }

    // Create a new inventory record
    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    // Update an inventory record
    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Integer id, @RequestBody Inventory inventoryDetails) {
        Inventory existingInventory = inventoryService.getInventoryById(id);
        return inventoryService.updateInventory(existingInventory, inventoryDetails);
    }

    // Delete an inventory record
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Integer id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        inventoryService.deleteInventory(inventory);
        return "Inventory with id " + id + " has been deleted.";
    }
}

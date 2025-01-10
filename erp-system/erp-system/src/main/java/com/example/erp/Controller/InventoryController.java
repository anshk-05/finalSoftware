package com.example.erp.Controller;

import com.example.erp.Model.Inventory;
import com.example.erp.Repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Integer id, @RequestBody Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id " + id));
        inventory.setStockLevel(inventoryDetails.getStockLevel());
        inventory.setReorderThreshold(inventoryDetails.getReorderThreshold());
        inventory.setProduct(inventoryDetails.getProduct());
        inventory.setStore(inventoryDetails.getStore());
        return inventoryRepository.save(inventory);
    }

    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Integer id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id " + id));
        inventoryRepository.delete(inventory);
        return "Inventory with id " + id + " has been deleted.";
    }
}

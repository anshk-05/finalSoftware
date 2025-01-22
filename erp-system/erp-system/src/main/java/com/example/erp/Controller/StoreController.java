package com.example.erp.Controller;

import com.example.erp.DTO.StoreDTO;
import com.example.erp.Model.Store;
import com.example.erp.Service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // Get all stores
    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores().stream()
                .map(store -> new StoreDTO(
                        store.getStoreId(),
                        store.getStoreName(),
                        store.getLocation(),
                        store.getManagerId(),
                        store.getTotalSales(),
                        store.getOperatingHours(),
                        store.getContactNumber()
                ))
                .collect(Collectors.toList());
    }

    // Get a single store
    @GetMapping("/{id}")
    public StoreDTO getStoreById(@PathVariable Integer id) {
        Store store = storeService.getStoreById(id);
        return new StoreDTO(
                store.getStoreId(),
                store.getStoreName(),
                store.getLocation(),
                store.getManagerId(),
                store.getTotalSales(),
                store.getOperatingHours(),
                store.getContactNumber()
        );
    }

    // Create a new store
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // Update a store
    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Integer id, @RequestBody Store storeDetails) {
        Store existingStore = storeService.getStoreById(id);
        return storeService.updateStore(existingStore, storeDetails);
    }

    // Delete a store
    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable Integer id) {
        Store store = storeService.getStoreById(id);
        storeService.deleteStore(store);
        return "Store with id " + id + " has been deleted.";
    }
}

package com.example.erp.Service;

import com.example.erp.Model.Store;
import com.example.erp.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // Get all stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Get store by ID
    public Store getStoreById(Integer id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id " + id));
    }

    // Create a new store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Update an existing store
    public Store updateStore(Store existingStore, Store storeDetails) {
        existingStore.setStoreName(storeDetails.getStoreName());
        existingStore.setLocation(storeDetails.getLocation());
        existingStore.setManagerId(storeDetails.getManagerId());
        existingStore.setTotalSales(storeDetails.getTotalSales());
        existingStore.setOperatingHours(storeDetails.getOperatingHours());
        existingStore.setContactNumber(storeDetails.getContactNumber());
        return storeRepository.save(existingStore);
    }

    // Delete a store
    public void deleteStore(Store store) {
        storeRepository.delete(store);
    }
}


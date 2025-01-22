package com.example.erp.Repository;

import com.example.erp.DTO.InventoryDTO;
import com.example.erp.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT new com.example.erp.DTO.InventoryDTO(i.inventoryId, i.product.productName, i.stockLevel, i.product.price, i.product.category) " +
            "FROM Inventory i")
    List<InventoryDTO> getInventoryWithProductDetails();

}

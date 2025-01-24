package com.example.erp.Repository;

import com.example.erp.Model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findBySupplier_SupplierId(Integer supplierId);
}

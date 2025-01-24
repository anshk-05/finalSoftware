package com.example.erp.Repository;

import com.example.erp.Model.PurchaseOrderProduct;
import com.example.erp.Model.PurchaseOrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProduct, PurchaseOrderProductId> {
}


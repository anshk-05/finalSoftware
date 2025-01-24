package com.example.erp.Repository;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT new com.example.erp.DTO.SalesDTO(" +
            "s.salesId, s.dateOfSale, s.totalAmount, s.paymentMethod, " +
            "st.storeName, e.name, " +
            "sp.product.productId, sp.product.productName, sp.quantity, sp.pricePerUnit) " +
            "FROM Sales s " +
            "JOIN s.store st " +
            "JOIN s.employee e " +
            "JOIN s.salesProducts sp " +
            "ORDER BY s.dateOfSale DESC")
    List<SalesDTO> getSalesWithDetails();


    @Query("SELECT SUM(s.totalAmount) FROM Sales s")
    Double getTotalRevenue();

    @Query("SELECT CAST(EXTRACT(MONTH FROM s.dateOfSale) AS integer) AS month, SUM(s.totalAmount) AS revenue " +
            "FROM Sales s GROUP BY EXTRACT(MONTH FROM s.dateOfSale) ORDER BY month")
    List<Object[]> getSalesTrends();



}

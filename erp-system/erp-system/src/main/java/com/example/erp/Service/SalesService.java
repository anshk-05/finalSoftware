package com.example.erp.Service;

import com.example.erp.Model.Sales;
import com.example.erp.Repository.SalesRepository;
import com.example.erp.Service.EmployeeService;
import com.example.erp.Service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final EmployeeService employeeService;
    private final StoreService storeService;

    public SalesService(SalesRepository salesRepository, EmployeeService employeeService, StoreService storeService) {
        this.salesRepository = salesRepository;
        this.employeeService = employeeService;
        this.storeService = storeService;
    }

    // Get all sales
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    // Get sales by ID
    public Sales getSalesById(Integer id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found with id " + id));
    }

    // Create a new sales record
    public Sales createSales(Sales sales) {
        // Ensure related entities exist via their services
        sales.setEmployee(employeeService.getEmployeeById(sales.getEmployee().getEmployeeId()));
        sales.setStore(storeService.getStoreById(sales.getStore().getStoreId()));
        return salesRepository.save(sales);
    }

    // Update an existing sales record
    public Sales updateSales(Sales existingSales, Sales salesDetails) {
        existingSales.setDateOfSale(salesDetails.getDateOfSale());
        existingSales.setTotalAmount(salesDetails.getTotalAmount());
        existingSales.setPaymentMethod(salesDetails.getPaymentMethod());
        existingSales.setEmployee(employeeService.getEmployeeById(salesDetails.getEmployee().getEmployeeId()));
        existingSales.setStore(storeService.getStoreById(salesDetails.getStore().getStoreId()));
        return salesRepository.save(existingSales);
    }

    // Delete a sales record
    public void deleteSales(Sales sales) {
        salesRepository.delete(sales);
    }


}

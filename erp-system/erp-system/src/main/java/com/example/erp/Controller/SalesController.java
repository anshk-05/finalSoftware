package com.example.erp.Controller;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.Sales;
import com.example.erp.Service.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // Get all sales
    @GetMapping
    public List<SalesDTO> getAllSales() {
        return salesService.getAllSales().stream()
                .map(sales -> new SalesDTO(
                        sales.getSalesId(),
                        sales.getDateOfSale().toString(),
                        sales.getTotalAmount(),
                        sales.getPaymentMethod(),
                        sales.getStore() != null ? sales.getStore().getStoreName() : null,
                        sales.getEmployee() != null ? sales.getEmployee().getName() : null
                ))
                .collect(Collectors.toList());
    }

    // Get a single sales record
    @GetMapping("/{id}")
    public SalesDTO getSalesById(@PathVariable Integer id) {
        Sales sales = salesService.getSalesById(id);
        return new SalesDTO(
                sales.getSalesId(),
                sales.getDateOfSale().toString(),
                sales.getTotalAmount(),
                sales.getPaymentMethod(),
                sales.getStore() != null ? sales.getStore().getStoreName() : null,
                sales.getEmployee() != null ? sales.getEmployee().getName() : null
        );
    }

    // Create a new sales record
    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.createSales(sales);
    }

    // Update a sales record
    @PutMapping("/{id}")
    public Sales updateSales(@PathVariable Integer id, @RequestBody Sales salesDetails) {
        Sales existingSales = salesService.getSalesById(id);
        return salesService.updateSales(existingSales, salesDetails);
    }

    // Delete a sales record
    @DeleteMapping("/{id}")
    public String deleteSales(@PathVariable Integer id) {
        Sales sales = salesService.getSalesById(id);
        salesService.deleteSales(sales);
        return "Sales with id " + id + " has been deleted.";
    }
}

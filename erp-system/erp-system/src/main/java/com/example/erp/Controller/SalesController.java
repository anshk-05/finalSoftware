package com.example.erp.Controller;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.Sales;
import com.example.erp.Service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // Get all sales (no changes needed)
    @GetMapping
    public List<SalesDTO> getAllSales() {
        return salesService.getAllSales();
    }

    // Create a new sale
    @PostMapping
    public ResponseEntity<String> createSales(@RequestBody SalesDTO salesDTO) {
        salesService.createSales(salesDTO);
        return ResponseEntity.ok("Sale created successfully!");
    }
}


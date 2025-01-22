package com.example.erp.Controller;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.Sales;
import com.example.erp.Service.SalesService;
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

    @GetMapping
    public List<SalesDTO> getAllSales() {
        return salesService.getAllSales();
    }

    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.createSales(sales);
    }
}

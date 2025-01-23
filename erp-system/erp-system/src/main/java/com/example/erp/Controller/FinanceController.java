package com.example.erp.Controller;

import com.example.erp.Service.FinanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/revenue")
    public Double getTotalRevenue() {
        return financeService.getTotalRevenue();
    }

    @GetMapping("/expenses")
    public Double getTotalExpenses() {
        return financeService.getTotalExpenses();
    }

    @GetMapping("/profit")
    public Double getProfit() {
        return financeService.getProfit();
    }

    @GetMapping("/sales-trends")
    public Map<String, Double> getSalesTrends() {
        return financeService.getSalesTrends();
    }
}

package com.example.erp.Service;

import com.example.erp.Repository.EmployeeRepository;
import com.example.erp.Repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private final SalesRepository salesRepository;
    private final EmployeeRepository employeeRepository;

    public FinanceService(SalesRepository salesRepository, EmployeeRepository employeeRepository) {
        this.salesRepository = salesRepository;
        this.employeeRepository = employeeRepository;
    }

    // Total Revenue
    public Double getTotalRevenue() {
        return salesRepository.getTotalRevenue();
    }

    // Total Expenses
    public Double getTotalExpenses() {
        Double salaries = employeeRepository.getTotalSalaries();
        return salaries != null ? salaries : 0.0;
    }

    // Profit Calculation
    public Double getProfit() {
        Double revenue = getTotalRevenue();
        Double expenses = getTotalExpenses();
        return revenue - expenses;
    }

    // Sales Trends
    public Map<String, Double> getSalesTrends() {
        List<Object[]> trends = salesRepository.getSalesTrends();
        return trends.stream()
                .collect(Collectors.toMap(
                        t -> "Month " + (Integer) t[0], // Cast t[0] to Integer
                        t -> (Double) t[1] // Cast t[1] to Double for revenue
                ));
    }

}

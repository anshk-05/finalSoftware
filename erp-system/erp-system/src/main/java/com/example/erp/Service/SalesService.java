package com.example.erp.Service;

import com.example.erp.DTO.SalesDTO;
import com.example.erp.Model.Sales;
import com.example.erp.Repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<SalesDTO> getAllSales() {
        return salesRepository.getSalesWithDetails();
    }

    public Sales createSales(Sales sales) {
        return salesRepository.save(sales);
    }
}

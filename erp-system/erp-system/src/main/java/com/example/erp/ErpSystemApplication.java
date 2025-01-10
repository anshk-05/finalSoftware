package com.example.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.erp.Model.Store;
import com.example.erp.Repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ErpSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(StoreRepository storeRepository) {
		return args -> {
			storeRepository.save(new Store(1, "Downtown Store", "123 Main St", 101, 15000.75, "9AM - 9PM", "123-456-7890"));
			storeRepository.save(new Store(2, "Uptown Store", "456 Elm St", 102, 20000.50, "10AM - 8PM", "987-654-3210"));
		};
	}

}

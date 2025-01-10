package com.example.erp.Repository;

import com.example.erp.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    // JpaRepository provides built-in methods like findAll(), findById(), save(), deleteById().
}

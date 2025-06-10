package com.InventoryManagement.project.repository;


import com.InventoryManagement.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsDeletedFalse();
    List<Product> findByTotalStockLessThanAndIsDeletedFalse(int threshold);
}

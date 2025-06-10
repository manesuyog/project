package com.InventoryManagement.project.repository;


import com.InventoryManagement.project.entity.Product;
import com.InventoryManagement.project.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProduct(Product product);
}

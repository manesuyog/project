package com.InventoryManagement.project.service;

import com.InventoryManagement.project.dto.ProductRequestDTO;
import com.InventoryManagement.project.dto.ProductResponseDTO;
import com.InventoryManagement.project.dto.StockUpdateDTO;
import com.InventoryManagement.project.entity.StockMovement;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO dto);

    ProductResponseDTO updateStock(Long productId, StockUpdateDTO dto);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProduct(Long id);

    List<ProductResponseDTO> getLowStockProducts(int threshold);

    List<StockMovement> getStockMovements(Long productId);

//    ProductResponseDTO mapToDTO(Product product);

}

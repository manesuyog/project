package com.InventoryManagement.project.service;

import com.InventoryManagement.project.dto.ProductRequestDTO;
import com.InventoryManagement.project.dto.ProductResponseDTO;
import com.InventoryManagement.project.dto.StockUpdateDTO;
import com.InventoryManagement.project.entity.Product;
import com.InventoryManagement.project.entity.StockMovement;
import com.InventoryManagement.project.repository.ProductRepository;
import com.InventoryManagement.project.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private StockMovementRepository movementRepo;

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .totalStock(dto.getTotalStock())
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        product = productRepo.save(product);
        return mapToDTO(product);
    }

    public ProductResponseDTO updateStock(Long productId, StockUpdateDTO dto) {
        Product product = productRepo.findById(productId).orElseThrow();

        if (dto.getType().equalsIgnoreCase("IN")) {
            product.setTotalStock(product.getTotalStock() + dto.getQuantity());
        } else if (dto.getType().equalsIgnoreCase("OUT")) {
            product.setTotalStock(product.getTotalStock() - dto.getQuantity());
        }
        product.setUpdatedAt(LocalDateTime.now());
        productRepo.save(product);

        movementRepo.save(StockMovement.builder()
                .product(product)
                .type(dto.getType())
                .quantity(dto.getQuantity())
                .movementTime(LocalDateTime.now())
                .build());

        return mapToDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepo.findByIsDeletedFalse().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProductResponseDTO getProduct(Long id) {
        return mapToDTO(productRepo.findById(id).orElseThrow());
    }

    public List<ProductResponseDTO> getLowStockProducts(int threshold) {
        return productRepo.findByTotalStockLessThanAndIsDeletedFalse(threshold).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<StockMovement> getStockMovements(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow();
        return movementRepo.findByProduct(product);
    }

    private ProductResponseDTO mapToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .totalStock(product.getTotalStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}

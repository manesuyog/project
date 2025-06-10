package com.InventoryManagement.project.controller;


import com.InventoryManagement.project.dto.ProductRequestDTO;
import com.InventoryManagement.project.dto.ProductResponseDTO;
import com.InventoryManagement.project.dto.StockUpdateDTO;
import com.InventoryManagement.project.entity.StockMovement;
import com.InventoryManagement.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private  ProductService productService;

    @PostMapping
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PostMapping("/{id}/stock")
    public ProductResponseDTO updateStock(@PathVariable Long id,
                                          @RequestBody StockUpdateDTO dto) {
        return productService.updateStock(id, dto);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/low-stock")
    public List<ProductResponseDTO> getLowStockProducts(@RequestParam int threshold) {
        return productService.getLowStockProducts(threshold);
    }

    @GetMapping("/{id}/movements")
    public List<StockMovement> getStockMovements(@PathVariable Long id) {
        return productService.getStockMovements(id);
    }
}
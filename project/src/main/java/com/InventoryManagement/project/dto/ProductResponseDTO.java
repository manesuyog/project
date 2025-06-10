package com.InventoryManagement.project.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private int totalStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
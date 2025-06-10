package com.InventoryManagement.project.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private String name;
    private String category;
    private String description;
    private int totalStock;
}
package com.InventoryManagement.project.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockUpdateDTO {
    private String type; // IN or OUT
    private int quantity;
}
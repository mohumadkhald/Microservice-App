package com.projects.inventoryservice.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class InventoryResponse {
    private String skuCode;
    private Boolean isInStock;

}

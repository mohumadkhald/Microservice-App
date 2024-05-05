package com.projects.inventoryservice.controller;

import com.projects.inventoryservice.dto.InventoryResponse;
import com.projects.inventoryservice.service.InventoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode)
    {

        return inventoryService.isInStock(skuCode);
    }

}

package com.projects.inventoryservice.service;

import com.projects.inventoryservice.dto.InventoryResponse;
import com.projects.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl {

    private  final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skyCode) {


        return inventoryRepository.findBySkuCodeIn(skyCode).stream().map(inventory ->
            InventoryResponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity()>0)
                    .build()
        ).collect(Collectors.toList());


    }
}

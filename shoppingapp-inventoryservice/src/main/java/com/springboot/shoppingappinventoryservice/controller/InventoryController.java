package com.springboot.shoppingappinventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.shoppingappinventoryservice.dto.InventoryResponse;
import com.springboot.shoppingappinventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventoryservice")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    
    
    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) 
    {
        
        return inventoryService.isInStock(skuCode);
    }
}

package com.example.Distributed.Application.Inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getStock(@PathVariable Long productId) {
        int stock = inventoryService.getStockForProductId(productId);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/{productId}/add-stock")
    public ResponseEntity<String> addStock(@PathVariable Long productId, @RequestParam int amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Amount must be greater than zero.");
        }
        inventoryService.addStockForProductId(productId, amount);
        return ResponseEntity.ok("Stock updated successfully.");

        // http://localhost:8080/inventory/1/add-stock?amount=20

    }
}

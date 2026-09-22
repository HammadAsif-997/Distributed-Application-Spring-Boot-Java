package com.example.Distributed.Application.Inventory;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private final Map<Long, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put(1L, 100);
        inventory.put(2L, 50);
        inventory.put(3L, 0);
    }

    
    /** 
     * @param productId
     * @return int
     */
    public int getStockForProductId(Long productId) {
        return inventory.getOrDefault(productId, 0); // Return 0 if product is not found
    }

    public void reduceStockForProductId(Long productId, int amount) {
        inventory.computeIfPresent(productId, (id, stock) -> Math.max(stock - amount, 0));
    }

    public void addStockForProductId(Long productId, int amount) {
        inventory.merge(productId, amount, Integer::sum); // Add stock or initialize it if not present
    }

    public void updateStockForProductId(Long productId, int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("Stock count cannot be negative.");
        }
        inventory.put(productId, newStock); // Overwrite or set stock value
    }
}

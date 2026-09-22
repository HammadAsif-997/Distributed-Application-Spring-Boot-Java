package com.example.Distributed.Application.AddToCart;

import com.example.Distributed.Application.Product.ProductModel;
import com.example.Distributed.Application.Product.Currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartModel {
    Map<ProductModel, Integer> products = new HashMap<>();
    private boolean voucherApplied = false;
    public BigDecimal originalTotalPrice = BigDecimal.ZERO; // Store the original price before discounts
    public BigDecimal discountedTotalPrice = BigDecimal.ZERO; // Store the price after applying discounts
    private Currency currency = Currency.EUR; // Default currency
    
    /** 
     * @return Map<ProductModel, Integer>
     */
    public Map<ProductModel, Integer> getProducts() {
        return products;
    }

    public void addProduct(ProductModel product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
        updatePrices();
    }

    public void removeProduct(ProductModel product) {
            products.remove(product);
            updatePrices();
    }

    // public BigDecimal calculateTotal() {
    //     return products.entrySet().stream()
    //             .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
    //             .sum();
    // }

    public BigDecimal calculateTotal() {
        
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Update original and discounted prices
    public void updatePrices() {
        originalTotalPrice = calculateTotal();
        discountedTotalPrice = voucherApplied ? originalTotalPrice.multiply(BigDecimal.valueOf(0.9)) : originalTotalPrice; // Apply 10% discount if voucher is applied
        
    }


    // Getters and Setters
    public boolean isVoucherApplied() {
        return voucherApplied;
    }

    public void setVoucherApplied(boolean voucherApplied) {
        this.voucherApplied = voucherApplied;
        updatePrices(); // Recalculate discounted price based on voucher status
    }

    public BigDecimal getOriginalTotalPrice() {
        return originalTotalPrice;
    }

    public BigDecimal getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }

    public void setDiscountedTotalPrice(BigDecimal discountedTotalPrice) {
        this.discountedTotalPrice = discountedTotalPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    
    
}


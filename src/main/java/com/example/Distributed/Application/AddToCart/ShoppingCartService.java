package com.example.Distributed.Application.AddToCart;

import com.example.Distributed.Application.Inventory.InventoryService;
import com.example.Distributed.Application.Product.Currency;
import com.example.Distributed.Application.Product.PriceCalculationService;
import com.example.Distributed.Application.Product.ProductModel;
import com.example.Distributed.Application.Product.ProductService;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartModel shoppingCart;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final PriceCalculationService priceCalculationService;

    public ShoppingCartService(ProductService productService, InventoryService inventoryService, PriceCalculationService priceCalculationService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.shoppingCart = new ShoppingCartModel(); // Initialize the cart
        this.priceCalculationService = priceCalculationService;
    }

    
    /** 
     * @return ShoppingCartModel
     */
    public ShoppingCartModel getShoppingCart() {
        return shoppingCart;
    }

    public String addProductToCart(Long productId, int quantity) {
        // Fetch the product
        ProductModel product = productService.getProductById(productId);
        if (product == null) {
            return "Product not found!";
        }

        // Check stock availability
        Integer currentStock = inventoryService.getStockForProductId(productId);
        if (currentStock == null || currentStock < quantity) {
            return "Not enough stock available!";
        }

        // Add product to cart
        shoppingCart.addProduct(product, quantity);

        // Reduce stock in inventory
        inventoryService.reduceStockForProductId(productId, quantity);

        return "Product added to cart successfully!";
    }

    public void removeProductFromCart(Long productId) {
        System.out.println("Removing product with ID: " + productId);
        
        // Fetch the quantity of the product being removed
        shoppingCart.products.entrySet().removeIf(entry -> {
            if (entry.getKey().getId().equals(productId)) {
                int removedQuantity = entry.getValue();
                
                // Return the stock to the inventory
                inventoryService.addStockForProductId(productId, removedQuantity);
                
                return true;
            }
            return false;
        });
        shoppingCart.updatePrices();
        System.out.println("Cart contents after removal: " + shoppingCart.products);
    }

    public BigDecimal calculateTotal() {
        
        return priceCalculationService.roundPrice(shoppingCart.calculateTotal());
    }

    public void applyVoucher() {
        if (!shoppingCart.isVoucherApplied()) {
            BigDecimal discountedPrice = priceCalculationService.applyVoucher(shoppingCart.getOriginalTotalPrice(), PriceCalculationService.VOUCHER_PERCENTAGE);
            shoppingCart.setDiscountedTotalPrice(discountedPrice);
            shoppingCart.setVoucherApplied(true);
        }
    }
    
    public void removeVoucher() {
        if (shoppingCart.isVoucherApplied()) {
            shoppingCart.setDiscountedTotalPrice(shoppingCart.getOriginalTotalPrice());
            shoppingCart.setVoucherApplied(false);
        }
    }

    public void convertCurrency(Currency toCurrency) {
        BigDecimal convertedTotalPrice = priceCalculationService.convertCurrency(shoppingCart.getDiscountedTotalPrice(), shoppingCart.getCurrency(), toCurrency);
        shoppingCart.setDiscountedTotalPrice(convertedTotalPrice);
        shoppingCart.setCurrency(toCurrency);
    }
    
    
}

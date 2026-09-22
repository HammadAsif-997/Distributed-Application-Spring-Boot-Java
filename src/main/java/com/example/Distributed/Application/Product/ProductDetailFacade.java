package com.example.Distributed.Application.Product;

import com.example.Distributed.Application.Inventory.InventoryService;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;  
    private final PriceCalculationService priceCalculationService;


    /**
     * Service for handling product details and inventory data.
     * Requires ProductService, InventoryService, PriceCalculationService for operation.
     */


    /**
     * Constructor for injecting dependencies.
     *
     * @param productService handles product-related operations.
     * @param inventoryService handles inventory-related operations.
     * @param priceCalculationService handles price-rounding operations.
     */
//    @Autowired
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService, PriceCalculationService priceCalculationService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        
        this.priceCalculationService = priceCalculationService;
    }

    
    /** 
     * @param productId
     * @return ProductDetailDTO
     */
    public ProductDetailDTO getProductDetail(Long productId) {
        ProductModel product = productService.getProductById(productId);
        if (product == null) {
            return null; // If no product is found, return null
        }

        // Get the stock information from InventoryService
        int stock = inventoryService.getStockForProductId(productId);
        boolean isSoldOut = stock == 0;

        System.out.println(product.getName());

        // Create and return the ProductDetailDTO
        return new ProductDetailDTO(
                product.getId(),
                product.getName(),
                product.getColor(),
                product.getSize(),
                priceCalculationService.roundPrice(product.getPrice()),
                stock,
                isSoldOut
        );
    }
}

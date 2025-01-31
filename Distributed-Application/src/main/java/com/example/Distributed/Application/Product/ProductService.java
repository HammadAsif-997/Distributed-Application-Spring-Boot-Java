package com.example.Distributed.Application.Product;

import com.example.Distributed.Application.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to products.
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final InventoryService inventoryService;
    private final PriceCalculationService priceCalculationService;

    /**
     * Constructor-based dependency injection.
     *
     * @param productRepository      Repository for performing CRUD operations on products.
     * @param inventoryService       Service for inventory-related operations.
     * @param priceCalculationService Service for price-related calculations.
     */
    @Autowired
    public ProductService(ProductRepository productRepository, InventoryService inventoryService, PriceCalculationService priceCalculationService) {
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
        this.priceCalculationService = priceCalculationService;
    }

    /**
     * Fetches all products from the database.
     *
     * @return List of all products.
     */
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Fetches a paginated list of products.
     *
     * @param pageable Pagination parameters.
     * @return Page of products.
     */
    public Page<ProductModel> getPaginatedProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Fetches a product by its ID.
     *
     * @param id ID of the product.
     * @return ProductModel if found.
     * @throws ProductNotFoundException If no product is found with the given ID.
     */
    public ProductModel getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    /**
     * Saves a new product to the database.
     *
     * @param product ProductModel to save.
     * @return Saved ProductModel.
     */
    public ProductModel saveProduct(ProductModel product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id ID of the product to delete.
     * @return True if the product was successfully deleted, false otherwise.
     */
    public boolean deleteProduct(Long id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false; // Return false if product ID doesn't exist
    }

    /**
     * Fetches products by their color (case-insensitive).
     *
     * @param color Color of the products.
     * @return List of products with the given color.
     */
    public List<ProductModel> getProductsByColor(String color) {
        List<ProductModel> products = productRepository.findByColorIgnoreCase(color);
        if (products.isEmpty()) {
            logger.warn("No products found with color: " + color);
        }
        return products;
    }

    /**
     * Fetches products by a name substring (case-insensitive).
     *
     * @param name Substring of the product name.
     * @return List of products containing the given name substring.
     */
    public List<ProductModel> getProductsByName(String name) {
        List<ProductModel> products = productRepository.findByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            logger.warn("No products found containing name: " + name);
        }
        return products;
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id             ID of the product to update.
     * @param updatedProduct ProductModel with updated details.
     * @return Updated ProductModel or null if the product ID doesn't exist.
     */
    public ProductModel updateProduct(Long id, ProductModel updatedProduct) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductModel existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setPrice(updatedProduct.getPrice());

            return productRepository.save(existingProduct);
        }
        return null; // Return null if product ID doesn't exist
    }

    /**
     * Fetches detailed information for a product by ID, including stock and availability.
     *
     * @param id ID of the product.
     * @return ProductDetailDTO containing detailed product information or null if the product is not found.
     */
    public ProductDetailDTO getProductDetail(Long id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductModel product = optionalProduct.get();
            int stock = inventoryService.getStockForProductId(id);
            boolean isSoldOut = stock == 0;

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
        return null; // Return null if no product is found
    }
}

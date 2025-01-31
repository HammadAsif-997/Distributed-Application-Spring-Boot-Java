package com.example.Distributed.Application.Product;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.Distributed.Application.Inventory.InventoryService;

// Controller for handling product-related requests
// @RestController
@RequestMapping("/products")
// @CrossOrigin(origins = "http://localhost:8080")  // Allow CORS for frontend at localhost:8080
@Controller
public class ProductController {

    // Dependencies: ProductService and InventoryService
    private final ProductService productService;
    private final InventoryService inventoryService;

    // Constructor for dependency injection
    public ProductController(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /**
     * Get a product by its ID.
     * @param id the ID of the product
     * @return ResponseEntity with product details or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {
        ProductModel product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Display the catalog page with paginated products.
     * @param pageable pagination details (size, page, etc.)
     * @param model model to hold the paginated products
     * @return name of the Thymeleaf template for the catalog page
     */
    @GetMapping("/catalog-paginated")
    public String catalogPaginated(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<ProductModel> products = productService.getPaginatedProducts(pageable);
        model.addAttribute("products", products);
        return "catalog";
    }


    
    /**
     * Retrieve all products or search by name if provided.
     * @param name optional product name for searching
     * @param model model to hold the list of products
     * @return name of the Thymeleaf template for the product page
     */

    @GetMapping
    public String getAllProducts(@RequestParam(required = false) String name, Model model) {
        List<ProductModel> products;
        if (name != null && !name.isEmpty()) {
            products = productService.getProductsByName(name);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products); // Add products to the model
        return "product"; // Return the name of the Thymeleaf template (product.html)
    }


    /**
     * Display the catalog page with all products.
     * @param model model to hold the list of products
     * @return name of the Thymeleaf template for the catalog page
     */

    @GetMapping("/catalog")
    public String showCatalogPage(Model model) {
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product"; // Maps to src/main/resources/templates/catalog.html
    }

    
    /**
     * Create a new product.
     * @param product the product details to save
     * @return ResponseEntity with the saved product details
     */
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        ProductModel savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * Update an existing product by its ID and optionally update its stock.
     * @param id the ID of the product
     * @param updatedProduct the updated product details
     * @param stock optional stock value to update
     * @return updated ProductModel
     */
    @PutMapping("/{id}")
    public ProductModel updateProduct(
            @PathVariable Long id,
            @RequestBody ProductModel updatedProduct,
            @RequestParam(required = false) Integer stock) {
        if (stock != null) inventoryService.updateStockForProductId(id, stock);
        return productService.updateProduct(id, updatedProduct);
    }

    /**
     * Delete a product by its ID.
     * @param id the ID of the product to delete
     * @return redirect to the catalog page
     */
    @DeleteMapping("/{id}")
    public String deleteProducts(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:catalog";
    }

    /**
     * Fetch products filtered by color.
     * @param color the color to filter products by
     * @param model model to hold the filtered products
     * @return name of the Thymeleaf template for the product page
     */
    @GetMapping("/color/{color}")
    public String searchProductsByColor(@PathVariable String color, Model model) {
        List<ProductModel> products = productService.getProductsByColor(color);
        model.addAttribute("products", products); // Add filtered products to the model
        return "product"; // Return the name of the Thymeleaf template (product.html)
    }

    /**
     * Search products by name.
     * @param name the name of the product to search for
     * @return list of products matching the name
     */
    @GetMapping("/search")
    public List<ProductModel> searchProductsByName(@RequestParam String name) {
        return productService.getProductsByName(name);
    }




}

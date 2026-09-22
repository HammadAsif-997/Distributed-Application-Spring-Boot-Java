package com.example.Distributed.Application;


import com.example.Distributed.Application.Product.ProductModel;
import com.example.Distributed.Application.Product.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saas/catalog")
public class SaaSCatalogController {

    private final ProductService productService;

    public SaaSCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getCatalog() {
        return productService.getAllProducts();
    }
}

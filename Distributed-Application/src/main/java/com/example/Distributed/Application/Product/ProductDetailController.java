package com.example.Distributed.Application.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Distributed.Application.Review.ReviewModel;
import com.example.Distributed.Application.Review.ReviewRepository;

@Controller
public class ProductDetailController {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    // Dependency injection of ProductDetailFacade to handle business logic
    private final ProductDetailFacade productDetailFacade;

    // Constructor for injecting dependencies
    public ProductDetailController(ProductDetailFacade productDetailFacade) {
        this.productDetailFacade = productDetailFacade;
    }

    /**
     * Handles the request to fetch and display detailed information about a product.
     * 
     * @param id    The ID of the product to retrieve.
     * @param model The Model object to pass data to the view.
     * @return The name of the Thymeleaf template to render (product-detail.html) or 
     *         a redirect to the catalog page if the product is not found.
     */
    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        // Fetch the product details using the facade
        ProductDetailDTO productDetailDTO = productDetailFacade.getProductDetail(id);
        
        List<ReviewModel> reviews = reviewRepository.findByProductId(id);
        model.addAttribute("reviews", reviews);
        model.addAttribute("productId", id);

        // If no product is found, redirect to the catalog page
        if (productDetailDTO == null) {
            return "redirect:/catalog";
        }

        // Add the product details to the model to be displayed in the view
        model.addAttribute("product", productDetailDTO);
        return "product-detail"; // Name of the Thymeleaf template (productDetail.html)
    }


    //    @GetMapping("/product/{productId}")
    // public String showProductDetail(@PathVariable String productId, Model model) {
    //     List<ReviewModel> reviews = reviewRepository.findByProductId(productId);
    //     model.addAttribute("reviews", reviews);
    //     model.addAttribute("productId", productId);
    //     return "product-detail";
    // }

}

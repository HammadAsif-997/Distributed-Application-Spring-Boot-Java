package com.example.Distributed.Application.AddToCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Distributed.Application.Product.Currency;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", shoppingCartService.getShoppingCart());
        model.addAttribute("total", shoppingCartService.calculateTotal());
    
        // model.addAttribute("discountedTotal", shoppingCartService.updatedPrice());
        return "cart"; // Maps to src/main/resources/templates/cart.html
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable Long id) {
        shoppingCartService.addProductToCart(id, 1); // Default quantity of 1
        return "redirect:/cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCartPost(@PathVariable Long id) {
        shoppingCartService.addProductToCart(id, 1); // Default quantity of 1
        return "redirect:/cart";
    }

    @GetMapping("/cart-remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        shoppingCartService.removeProductFromCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/voucher")
    public String applyVoucher() {
        shoppingCartService.applyVoucher();
        return "redirect:/cart";
    }

    @GetMapping("/cart/removevoucher")
    public String removeVoucher() {
        shoppingCartService.removeVoucher();
        return "redirect:/cart";
    }


    @GetMapping("/cart/dollar")
    public String convertToDollar() {
        shoppingCartService.convertCurrency(Currency.USD);
        return "redirect:/cart";
    }

    @GetMapping("/cart/eur")
    public String convertToEuro() {
        shoppingCartService.convertCurrency(Currency.EUR);
        return "redirect:/cart";
    }

}

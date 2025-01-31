package com.example.Distributed.Application.Product;

import java.math.BigDecimal;

/**
 * A Data Transfer Object (DTO) class for transferring detailed product information.
 * This class is used to encapsulate product data to be sent to the client or view layer.
 */
public class ProductDetailDTO {

    // Fields to store product details
    private Long id; // Unique identifier for the product
    private String name; // Name of the product
    private String color; // Color of the product
    private String size; // Size of the product
    private BigDecimal price; // Price of the product
    private Integer stock; // Stock quantity of the product
    private boolean isSoldOut; // Indicates whether the product is sold out

    // Default constructor (required for frameworks like Hibernate)
    public ProductDetailDTO() {}

    /**
     * Constructor to initialize all fields of the product detail.
     * 
     * @param id        Unique identifier for the product.
     * @param name      Name of the product.
     * @param color     Color of the product.
     * @param size      Size of the product.
     * @param price     Price of the product.
     * @param stock     Stock quantity of the product.
     * @param isSoldOut Indicates whether the product is sold out.
     */
    public ProductDetailDTO(Long id, String name, String color, String size, BigDecimal price, Integer stock, boolean isSoldOut) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.isSoldOut = isSoldOut;
    }

    /**
     * Constructor to initialize product details without stock and sold-out status.
     * 
     * @param id    Unique identifier for the product.
     * @param name  Name of the product.
     * @param color Color of the product.
     * @param size  Size of the product.
     * @param price Price of the product.
     */
    public ProductDetailDTO(Long id, String name, String color, String size, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    /**
     * Constructor to initialize product details without stock information.
     * 
     * @param id        Unique identifier for the product.
     * @param name      Name of the product.
     * @param color     Color of the product.
     * @param size      Size of the product.
     * @param price     Price of the product.
     * @param isSoldOut Indicates whether the product is sold out.
     */
    public ProductDetailDTO(Long id, String name, String color, String size, BigDecimal price, boolean isSoldOut) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    // Getters and setters for accessing and modifying fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}

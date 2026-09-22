package com.example.Distributed.Application.Product;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing a product in the system.
 * This class is mapped to a database table for persisting product data.
 */
@Entity
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique ID values for each product
    private Long id; // Unique identifier for the product
    private String name; // Name of the product
    private String color; // Color of the product
    private String size; // Size of the product
    private BigDecimal price; // Price of the product

    // Default constructor (required by JPA)
    public ProductModel() {}

    /**
     * Constructor to initialize a product without an ID (useful for creating new products).
     *
     * @param name  Name of the product.
     * @param color Color of the product.
     * @param size  Size of the product.
     * @param price Price of the product.
     */
    public ProductModel(String name, String color, String size, BigDecimal price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    /**
     * Constructor to initialize a product with an ID (useful for updating or representing existing products).
     *
     * @param id    Unique identifier for the product.
     * @param name  Name of the product.
     * @param color Color of the product.
     * @param size  Size of the product.
     * @param price Price of the product.
     */
    public ProductModel(Long id, String name, String color, String size, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    // Getters and Setters for accessing and modifying fields

    /**
     * Gets the product ID.
     *
     * @return Long - The unique identifier for the product.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     *
     * @param id The unique identifier for the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     *
     * @return String - The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product color.
     *
     * @return String - The color of the product.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the product color.
     *
     * @param color The color of the product.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the product size.
     *
     * @return String - The size of the product.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the product size.
     *
     * @param size The size of the product.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the product price.
     *
     * @return BigDecimal - The price of the product.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price The price of the product.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Overrides the default `toString` method to provide a readable representation of the product.
     *
     * @return String - A string representation of the product's details.
     */
    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }
}

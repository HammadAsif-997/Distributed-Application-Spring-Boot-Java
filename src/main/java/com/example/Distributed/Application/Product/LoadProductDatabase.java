package com.example.Distributed.Application.Product;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Configuration
public class LoadProductDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            repository.save(new ProductModel("Shirt", "Black", "M", new BigDecimal(99.999)));
            repository.save(new ProductModel("Jeans", "Blue", "L", new BigDecimal(49.99)));
            repository.save(new ProductModel("T-Shirt", "Red", "S", new BigDecimal(14.99)));
            repository.save(new ProductModel("Shoes", "Black", "42", new BigDecimal(89.99)));
            repository.save(new ProductModel("Hat", "White", "One Size", new BigDecimal(9.99)));
            repository.save(new ProductModel("Jacket", "Green", "M", new BigDecimal(79.99)));
            repository.save(new ProductModel("Scarf", "Blue", "One Size", new BigDecimal(14.99)));
            repository.save(new ProductModel("Sweater", "Gray", "L", new BigDecimal(39.99)));
            repository.save(new ProductModel("Socks", "Black", "M", new BigDecimal(4.99)));
            repository.save(new ProductModel("Belt", "Brown", "One Size", new BigDecimal(24.99)));

            repository.findAll().forEach(product -> logger.info("Loaded: " + product));
        };
    }
}

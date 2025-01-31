
package com.example.Distributed.Application.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    // Method to find products by color
    List<ProductModel> findByColorIgnoreCase(String color);

    // Method to find products containing a specific name
    List<ProductModel> findByNameContainingIgnoreCase(String name);

    Page<ProductModel> findAll(Pageable pageable);

}

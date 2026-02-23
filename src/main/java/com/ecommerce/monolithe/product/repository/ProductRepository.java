package com.ecommerce.monolithe.product.repository;

import com.ecommerce.monolithe.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Permet de chercher des produits par nom (ignorant la casse)
    List<Product> findByNameContainingIgnoreCase(String name);
}
package com.ecommerce.product_service.repository;

// ─── Import du modèle Product ──────────────────────────────────
import com.ecommerce.product_service.model.Product;

// ─── Import Spring Data JPA ────────────────────────────────────
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository fournit déjà : findAll, findById, save, deleteById...
}

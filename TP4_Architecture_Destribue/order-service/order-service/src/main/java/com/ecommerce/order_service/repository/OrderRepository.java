package com.ecommerce.order_service.repository;

// ─── Import du modèle Order ────────────────────────────────────
import com.ecommerce.order_service.model.Order;

// ─── Import Spring Data JPA ────────────────────────────────────
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
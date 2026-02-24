package com.ecommerce.monolithe.product.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // Relation inverse : une catégorie a plusieurs produits
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
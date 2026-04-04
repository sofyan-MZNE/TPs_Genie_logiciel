package com.ecommerce.product_service.service;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }
    public Product updateStock(Long id, Integer quantity) {

        // Chercher le produit, erreur si introuvable
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Produit introuvable avec l'id: " + id));

        // Vérifier si le stock est suffisant
        if (product.getStock() < quantity) {
            throw new RuntimeException(
                    "Stock insuffisant. Disponible: " + product.getStock()
                            + ", Demandé: " + quantity);
        }

        // Décrémenter le stock
        product.setStock(product.getStock() - quantity);

        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return productRepository.save(existingProduct);
                });
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }
}

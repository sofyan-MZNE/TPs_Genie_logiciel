package com.ecommerce.order_service.client;

import com.ecommerce.order_service.dto.ProductDto;
import com.ecommerce.order_service.dto.UpdateStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {

    // Récupérer un produit par son ID
    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable Long id);

    // Mettre à jour le stock après une commande
    @PutMapping("/api/products/{id}/stock")
    ProductDto updateStock(@PathVariable Long id,
                           @RequestBody UpdateStockRequest request);
}
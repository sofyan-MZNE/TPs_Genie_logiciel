package com.ecommerce.monolithe.product.service;

import com.ecommerce.monolithe.product.dto.CreateProductRequest;
import com.ecommerce.monolithe.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO createProduct(CreateProductRequest request);

    ProductDTO updateProduct(Long id, CreateProductRequest request);

    void deleteProduct(Long id);
}
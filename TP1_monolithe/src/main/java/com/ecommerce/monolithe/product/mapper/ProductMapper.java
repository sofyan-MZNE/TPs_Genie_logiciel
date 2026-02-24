package com.ecommerce.monolithe.product.mapper;

import com.ecommerce.monolithe.product.dto.CreateProductRequest;
import com.ecommerce.monolithe.product.dto.ProductDTO;
import com.ecommerce.monolithe.product.model.Product;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Entity → DTO (pour les réponses)
    ProductDTO toDTO(Product product);

    // Liste d'entités → Liste de DTOs
    List<ProductDTO> toDTOList(List<Product> products);

    // Request → Entity (pour la création)
    Product toEntity(CreateProductRequest request);

    // Mise à jour d'une entité existante (pour le update)
    @Mapping(target = "id", ignore = true)
    void updateEntity(CreateProductRequest request, @MappingTarget Product product);
}
package com.ecommerce.monolithe.product.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock = 0;
}

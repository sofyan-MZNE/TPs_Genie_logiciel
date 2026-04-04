package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.dto.ProductDto;
import com.ecommerce.order_service.dto.UpdateStockRequest;
import com.ecommerce.order_service.exeptions.InsufficientStockException;
import com.ecommerce.order_service.exeptions.ProductNotFoundException;
import com.ecommerce.order_service.exeptions.ServiceUnavailableException;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Long productId, Integer quantity) {
        ProductDto product = fetchProduct(productId);
        validateStock(product, quantity);
        updateProductStock(productId, quantity);

        Order order = new Order();
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    private ProductDto fetchProduct(Long productId) {
        try {
            ProductDto product = productClient.getProductById(productId);
            if (product == null) {
                throw new ProductNotFoundException(productId);
            }
            return product;
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(productId);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("product-service");
        }
    }

    private void validateStock(ProductDto product, Integer quantity) {
        int availableStock = product.getStock() == null ? 0 : product.getStock();
        if (availableStock < quantity) {
            throw new InsufficientStockException(availableStock, quantity);
        }
    }

    private void updateProductStock(Long productId, Integer quantity) {
        try {
            productClient.updateStock(productId, new UpdateStockRequest(quantity));
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(productId);
        } catch (FeignException.BadRequest e) {
            throw new InsufficientStockException(0, quantity);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("product-service");
        }
    }
}

package com.ecommerce.order_service.controller;

// ─── Imports internes ──────────────────────────────────────────
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.service.OrderService;

// ─── Import Lombok ─────────────────────────────────────────────
import lombok.RequiredArgsConstructor;

// ─── Imports Spring Web ────────────────────────────────────────
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// ─── Import Java ───────────────────────────────────────────────
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestParam Long productId,
                             @RequestParam Integer quantity) {
        return orderService.createOrder(productId, quantity);
    }
}
package com.ecommerce.order_service;

// ─── Imports Spring Boot ───────────────────────────────────────
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ─── Import Eureka Client ──────────────────────────────────────
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// ─── Import OpenFeign ──────────────────────────────────────────
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
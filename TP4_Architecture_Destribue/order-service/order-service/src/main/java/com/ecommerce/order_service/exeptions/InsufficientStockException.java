package com.ecommerce.order_service.exeptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(int available, int requested) {
        super("Stock insuffisant. Disponible: " + available
                + ", Demandé: " + requested);
    }
}
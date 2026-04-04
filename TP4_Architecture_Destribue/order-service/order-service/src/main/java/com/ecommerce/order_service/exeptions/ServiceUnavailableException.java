package com.ecommerce.order_service.exeptions;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String service) {
        super("Le service " + service + " est temporairement indisponible.");
    }
}
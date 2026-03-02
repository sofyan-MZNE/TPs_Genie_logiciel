package com.ecommerce.monolithe.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
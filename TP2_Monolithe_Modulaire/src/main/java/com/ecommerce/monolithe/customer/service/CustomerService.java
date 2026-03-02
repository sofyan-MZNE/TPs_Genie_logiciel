package com.ecommerce.monolithe.customer.service;

import com.ecommerce.monolithe.customer.dto.CreateCustomerRequest;
import com.ecommerce.monolithe.customer.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CreateCustomerRequest request);

    CustomerDTO updateCustomer(Long id, CreateCustomerRequest request);

    void deleteCustomer(Long id);

    // Méthode demandée dans l'exercice
    boolean customerExists(Long id);
}
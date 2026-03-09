package com.ecommerce.monolithe.customer.service;

import com.ecommerce.monolithe.customer.dto.CreateCustomerRequest;
import com.ecommerce.monolithe.customer.dto.CustomerDTO;
import com.ecommerce.monolithe.customer.mapper.CustomerMapper;
import com.ecommerce.monolithe.customer.model.Customer;
import com.ecommerce.monolithe.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + id));
        return mapper.toDTO(customer);
    }

    @Override
    public CustomerDTO createCustomer(CreateCustomerRequest request) {
        // Vérifier si l'email existe déjà
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already exists: " + request.getEmail());
        }
        Customer customer = mapper.toEntity(request);
        Customer saved = repository.save(customer);
        return mapper.toDTO(saved);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CreateCustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + id));
        mapper.updateEntity(request, customer);
        Customer updated = repository.save(customer);
        return mapper.toDTO(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Customer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean customerExists(Long id) {
        return repository.existsById(id);
    }
}
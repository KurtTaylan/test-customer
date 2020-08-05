package com.delivery.customer.service.domain.customer;

import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryCustomerService {

    private final CustomerRepository customerRepository;


    public Optional<Customer> retrieveCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> retrieveCustomerByContacts(String email, String phoneNumber) {
        return customerRepository.findByEmailAndPhoneNumber(email, phoneNumber);
    }

    public Page<Customer> paginationOverCustomer(int page, int count) {
        PageRequest pageable = PageRequest.of(page - 1, count);
        return customerRepository.findAll(pageable);
    }

    public List<Customer> searchCustomersByCity(String name) {
        return customerRepository.findAllByAddressSetCityLike(name);
    }

    public List<Customer> searchCustomersByPhonePrefix(String prefix) {
        return customerRepository.findAllByPhoneNumberStartsWith(prefix);
    }
}

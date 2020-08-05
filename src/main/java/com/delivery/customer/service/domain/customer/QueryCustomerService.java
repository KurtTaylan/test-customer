package com.delivery.customer.service.domain.customer;

import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.repository.AddressRepository;
import com.delivery.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryCustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;


    public Optional<Customer> retrieveCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> retrieveCustomerByContacts(String email, String phoneNumber) {
        return customerRepository.findByEmailAndPhoneNumber(email, phoneNumber);
    }

    public Optional<Address> retrieveAddressById(Long addressId) {
        return addressRepository.findById(addressId);
    }
}

package com.delivery.customer.service.domain.customer;


import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.enumtype.AddressEnumType;
import com.delivery.customer.model.event.domain.address.CustomerAddressCreatedEvent;
import com.delivery.customer.model.event.domain.address.CustomerAddressDeletedEvent;
import com.delivery.customer.model.event.domain.customer.CustomerCreatedEvent;
import com.delivery.customer.model.event.payload.address.AddressPayload;
import com.delivery.customer.model.event.payload.customer.CustomerPayload;
import com.delivery.customer.model.exception.BusinessException;
import com.delivery.customer.service.repository.AddressRepository;
import com.delivery.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandCustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final QueryCustomerService queryCustomerService;
    private final ApplicationEventPublisher applicationEventPublisher;


    public Customer createCustomer(CustomerCreateRequest customerCreateRequest) {
        Customer customer = generateNewCustomer(customerCreateRequest);
        Customer persistedCustomer = customerRepository.save(customer);
        log.debug("Customer is Created: {}", persistedCustomer);
        applicationEventPublisher.publishEvent(new CustomerCreatedEvent(this, new CustomerPayload(persistedCustomer)));
        return persistedCustomer;
    }

    @Transactional
    public Address createCustomerAddress(Long customerId, CustomerAddressCreateRequest customerAddressCreateRequest) {
        Customer customer = queryCustomerService.retrieveCustomerById(customerId).get();
        Address newAddress = generateCustomerNewAddress(customer, customerAddressCreateRequest);
        Address persistedAddress = addressRepository.save(newAddress);
        log.debug("Customer Address is Created: {}", persistedAddress);
        applicationEventPublisher.publishEvent(new CustomerAddressCreatedEvent(this, new AddressPayload(persistedAddress)));
        return persistedAddress;
    }

    @Transactional
    public void deleteCustomerAddress(Long customerId, Long addressId) {
        Optional<Customer> optionalCustomer = queryCustomerService.retrieveCustomerById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new BusinessException("Requested Customer is not exist");
        }

        Customer customer = optionalCustomer.get();
        Optional<Address> optionalAddress = findRequestedAddress(addressId, customer);
        if (optionalAddress.isEmpty()) {
            throw new BusinessException("Requested Customer Address is not exist");
        } else {
            Address address = optionalAddress.get();
            customer.getAddressSet().remove(address);
            customerRepository.save(customer);
            log.debug("Customer Address is Deleted: {}", address);
            applicationEventPublisher.publishEvent(new CustomerAddressDeletedEvent(this, new AddressPayload(address)));
        }
    }

    private Optional<Address> findRequestedAddress(Long addressId, Customer customer) {
        return customer.getAddressSet().stream()
                .filter(addressCandidate -> Objects.equals(addressId, addressCandidate.getId()))
                .findFirst();
    }

    private Customer generateNewCustomer(CustomerCreateRequest customerCreateRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerCreateRequest.getFirstName());
        customer.setLastName(customerCreateRequest.getLastName());
        customer.setEmail(customerCreateRequest.getEmail());
        customer.setPhoneNumber(customerCreateRequest.getPhoneNumber());
        return customer;
    }

    private Address generateCustomerNewAddress(Customer customer, CustomerAddressCreateRequest customerAddressCreateRequest) {
        Address address = new Address();
        address.setCustomer(customer);
        address.setType(AddressEnumType.valueOf(customerAddressCreateRequest.getType()));
        address.setCountry(customerAddressCreateRequest.getCountry());
        address.setCity(customerAddressCreateRequest.getCity());
        address.setLine(customerAddressCreateRequest.getLine());
        return address;
    }
}

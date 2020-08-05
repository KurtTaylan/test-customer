package com.delivery.customer.service.validation;

import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.enumtype.AddressEnumType;
import com.delivery.customer.model.exception.ValidationException;
import com.delivery.customer.service.domain.customer.QueryCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidationCustomerService {

    private final QueryCustomerService queryCustomerService;

    public void validateForCreation(CustomerCreateRequest customerCreateRequest) {
        Optional<Customer> optionalCustomer = queryCustomerService.retrieveCustomerByContacts(customerCreateRequest.getEmail(), customerCreateRequest.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            throw new ValidationException("Requested Customer is Already Exist!");
        }
        log.debug("Request Validation is passed: {}", customerCreateRequest);
    }


    @Transactional
    public void validateForCreationAddress(Long customerId, CustomerAddressCreateRequest customerAddressCreateRequest) {
        try {
            AddressEnumType.valueOf(customerAddressCreateRequest.getType());
        } catch (Exception exception) {
            log.error("Given enumtype is not exist");
            throw new ValidationException("Address Type is wrong, Options: 1- business, 2- home");
        }

        Optional<Customer> optionalCustomer = queryCustomerService.retrieveCustomerById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ValidationException("Customer is not Exist!");
        }

        validateCustomerAddressNotExist(customerAddressCreateRequest, optionalCustomer);

        log.debug("Request Validation is passed: customer Id: {}, specs: {}", customerId, customerAddressCreateRequest);
    }

    private void validateCustomerAddressNotExist(CustomerAddressCreateRequest customerAddressCreateRequest, Optional<Customer> optionalCustomer) {
        Customer customer = optionalCustomer.get();
        Address candidate = generateCandidate(customerAddressCreateRequest);
        Set<Address> addressSet = customer.getAddressSet();
        if (addressSet.contains(candidate)) {
            throw new ValidationException("Requested Customer Address is Already Exist!");
        }
    }

    private Address generateCandidate(CustomerAddressCreateRequest customerAddressCreateRequest) {
        Address candidate = new Address();
        candidate.setCity(customerAddressCreateRequest.getCity());
        candidate.setCountry(customerAddressCreateRequest.getCountry());
        candidate.setType(AddressEnumType.valueOf(customerAddressCreateRequest.getType()));
        candidate.setLine(customerAddressCreateRequest.getLine());
        return candidate;
    }
}

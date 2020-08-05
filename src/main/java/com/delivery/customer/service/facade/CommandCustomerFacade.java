package com.delivery.customer.service.facade;

import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.dto.response.CustomerAddressCreateResponse;
import com.delivery.customer.model.dto.response.CustomerAddressDeleteResponse;
import com.delivery.customer.model.dto.response.CustomerCreateResponse;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.domain.customer.CommandCustomerService;
import com.delivery.customer.service.mapper.MappingCustomerService;
import com.delivery.customer.service.validation.ValidationCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandCustomerFacade {

    private final ValidationCustomerService validationCustomerService;
    private final CommandCustomerService commandCustomerService;
    private final MappingCustomerService mappingCustomerService;


    public CustomerCreateResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        log.info("Create Customer Request is fetched!, specs: {}", customerCreateRequest);
        validationCustomerService.validateForCreation(customerCreateRequest);
        Customer createdCustomer = commandCustomerService.createCustomer(customerCreateRequest);
        return mappingCustomerService.mapForCreation(createdCustomer);
    }

    public CustomerAddressCreateResponse createCustomerAddress(Long customerId, CustomerAddressCreateRequest customerAddressCreateRequest) {
        log.info("Create Customer Address Request is fetched!, customer Id: {} ,specs: {}", customerId, customerAddressCreateRequest);
        validationCustomerService.validateForCreationAddress(customerId, customerAddressCreateRequest);
        Address createdCustomerAddress = commandCustomerService.createCustomerAddress(customerId, customerAddressCreateRequest);
        return mappingCustomerService.mapForCreationAddress(createdCustomerAddress);
    }

    public CustomerAddressDeleteResponse deleteCustomerAddress(Long customerId, Long addressId) {
        log.info("Delete Customer Address Request is fetched!, customer Id: {} ,address Id: {}", customerId, addressId);
        commandCustomerService.deleteCustomerAddress(customerId, addressId);
        return mappingCustomerService.mapForDeletionAddress(customerId, addressId);
    }
}

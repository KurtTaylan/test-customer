package com.delivery.customer.service.mapper;

import com.delivery.customer.model.dto.domain.AddressDto;
import com.delivery.customer.model.dto.domain.CustomerDto;
import com.delivery.customer.model.dto.response.CustomerAddressCreateResponse;
import com.delivery.customer.model.dto.response.CustomerAddressDeleteResponse;
import com.delivery.customer.model.dto.response.CustomerCreateResponse;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.mapper.AddressMapper;
import com.delivery.customer.model.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MappingCustomerService {

    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;


    public CustomerCreateResponse mapForCreation(Customer createdCustomer) {
        CustomerDto customerDto = customerMapper.toDto(createdCustomer);

        CustomerCreateResponse customerCreateResponse = new CustomerCreateResponse();
        customerCreateResponse.setCustomer(customerDto);
        return customerCreateResponse;
    }

    public CustomerAddressCreateResponse mapForCreationAddress(Address createdCustomerAddress) {
        AddressDto addressDto = addressMapper.toDto(createdCustomerAddress);

        CustomerAddressCreateResponse customerAddressCreateResponse = new CustomerAddressCreateResponse();
        customerAddressCreateResponse.setAddress(addressDto);
        return customerAddressCreateResponse;
    }

    public CustomerAddressDeleteResponse mapForDeletionAddress(Long customerId, Long addressId) {
        CustomerAddressDeleteResponse customerAddressDeleteResponse = new CustomerAddressDeleteResponse();
        customerAddressDeleteResponse.setDeletedCustomerId(customerId);
        customerAddressDeleteResponse.setDeletedAddressId(addressId);
        return customerAddressDeleteResponse;
    }
}

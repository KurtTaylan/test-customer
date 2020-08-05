package com.delivery.customer.service.mapper;

import com.delivery.customer.model.dto.domain.AddressDto;
import com.delivery.customer.model.dto.domain.CustomerDto;
import com.delivery.customer.model.dto.response.*;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.mapper.AddressMapper;
import com.delivery.customer.model.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public CustomerPaginationResponse mapForPagination(Page<Customer> paginationCustomer, int page) {
        CustomerPaginationResponse customerPaginationResponse = new CustomerPaginationResponse();
        customerPaginationResponse.setList(customerMapper.toDtoList(paginationCustomer.getContent()));
        customerPaginationResponse.setPage(page);
        customerPaginationResponse.setCount(paginationCustomer.getSize());
        customerPaginationResponse.setTotalCount(paginationCustomer.getTotalElements());
        return customerPaginationResponse;
    }

    public CustomerIndividualResponse mapForRetrieveIndividual(Optional<Customer> optionalCustomer) {
        CustomerIndividualResponse customerIndividualResponse = new CustomerIndividualResponse();
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customerIndividualResponse.setCustomer(customerMapper.toDto(customer));
        }

        return customerIndividualResponse;
    }

    public CustomerSearchResponse mapForSearch(List<Customer> searchCustomer) {
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setList(customerMapper.toDtoList(searchCustomer));
        customerSearchResponse.setTotalCount(customerSearchResponse.getList().size());
        return customerSearchResponse;
    }
}

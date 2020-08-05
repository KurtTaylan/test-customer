package com.delivery.customer.service.facade;

import com.delivery.customer.BaseUnitTest;
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
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandCustomerFacadeTest extends BaseUnitTest {


    @InjectMocks
    private CommandCustomerFacade sut;

    @Mock
    private ValidationCustomerService validationCustomerService;

    @Mock
    private CommandCustomerService commandCustomerService;

    @Mock
    private MappingCustomerService mappingCustomerService;


    @Test
    void should_createCustomer() {
        // given
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();

        doNothing().when(validationCustomerService).validateForCreation(customerCreateRequest);

        Customer mockCustomer = new Customer();
        when(commandCustomerService.createCustomer(customerCreateRequest)).thenReturn(mockCustomer);

        CustomerCreateResponse mockResponse = new CustomerCreateResponse();
        when(mappingCustomerService.mapForCreation(mockCustomer)).thenReturn(mockResponse);

        // when
        sut.createCustomer(customerCreateRequest);

        // then
        InOrder inOrder = inOrder(validationCustomerService, commandCustomerService, mappingCustomerService);
        inOrder.verify(validationCustomerService).validateForCreation(customerCreateRequest);
        inOrder.verify(commandCustomerService).createCustomer(customerCreateRequest);
        inOrder.verify(mappingCustomerService).mapForCreation(mockCustomer);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_createCustomerAddress() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();

        doNothing().when(validationCustomerService).validateForCreationAddress(customerId, customerAddressCreateRequest);

        Address mockAddress = new Address();
        when(commandCustomerService.createCustomerAddress(customerId, customerAddressCreateRequest)).thenReturn(mockAddress);

        CustomerAddressCreateResponse mockResponse = new CustomerAddressCreateResponse();
        when(mappingCustomerService.mapForCreationAddress(mockAddress)).thenReturn(mockResponse);

        // when
        sut.createCustomerAddress(customerId, customerAddressCreateRequest);

        // then
        InOrder inOrder = inOrder(validationCustomerService, commandCustomerService, mappingCustomerService);
        inOrder.verify(validationCustomerService).validateForCreationAddress(customerId, customerAddressCreateRequest);
        inOrder.verify(commandCustomerService).createCustomerAddress(customerId, customerAddressCreateRequest);
        inOrder.verify(mappingCustomerService).mapForCreationAddress(mockAddress);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_deleteCustomerAddress() {
        // given
        Long customerId = 1l;
        Long addressId = 1l;

        doNothing().when(commandCustomerService).deleteCustomerAddress(customerId, addressId);

        CustomerAddressDeleteResponse mockResponse = new CustomerAddressDeleteResponse();
        when(mappingCustomerService.mapForDeletionAddress(customerId, addressId)).thenReturn(mockResponse);

        // when
        sut.deleteCustomerAddress(customerId, addressId);

        // then
        InOrder inOrder = inOrder(commandCustomerService, mappingCustomerService);
        inOrder.verify(commandCustomerService).deleteCustomerAddress(customerId, addressId);
        inOrder.verify(mappingCustomerService).mapForDeletionAddress(customerId, addressId);
        inOrder.verifyNoMoreInteractions();
    }
}
package com.delivery.customer.service.domain.customer;

import com.delivery.customer.BaseUnitTest;
import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.event.domain.address.CustomerAddressCreatedEvent;
import com.delivery.customer.model.event.domain.address.CustomerAddressDeletedEvent;
import com.delivery.customer.model.event.domain.customer.CustomerCreatedEvent;
import com.delivery.customer.model.exception.BusinessException;
import com.delivery.customer.model.exception.ValidationException;
import com.delivery.customer.service.repository.AddressRepository;
import com.delivery.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

class CommandCustomerServiceTest extends BaseUnitTest {

    @InjectMocks
    private CommandCustomerService sut;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private QueryCustomerService queryCustomerService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;


    @Test
    void should_createCustomer() {
        // given
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();

        Customer mockCustomer = new Customer();

        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        // when
        sut.createCustomer(customerCreateRequest);

        // then
        InOrder inOrder = inOrder(customerRepository, applicationEventPublisher);
        inOrder.verify(customerRepository).save(any(Customer.class));
        inOrder.verify(applicationEventPublisher).publishEvent(any(CustomerCreatedEvent.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_createCustomerAddress() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();
        customerAddressCreateRequest.setType("home");

        Customer mockCustomer = new Customer();
        when(queryCustomerService.retrieveCustomerById(customerId)).thenReturn(Optional.of(mockCustomer));

        Address mockAddress = new Address();
        when(addressRepository.save(any(Address.class))).thenReturn(mockAddress);

        // when
        sut.createCustomerAddress(customerId, customerAddressCreateRequest);

        // then
        InOrder inOrder = inOrder(queryCustomerService, addressRepository, applicationEventPublisher);
        inOrder.verify(queryCustomerService).retrieveCustomerById(customerId);
        inOrder.verify(addressRepository).save(any(Address.class));
        inOrder.verify(applicationEventPublisher).publishEvent(any(CustomerAddressCreatedEvent.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_deleteCustomerAddress() {
        // given
        Long customerId = 1l;
        Long addressId = 1l;

        Address mockAddress = new Address();
        mockAddress.setId(1l);
        Customer mockCustomer = new Customer();
        mockCustomer.getAddressSet().add(mockAddress);
        when(queryCustomerService.retrieveCustomerById(customerId)).thenReturn(Optional.of(mockCustomer));

        // when
        sut.deleteCustomerAddress(customerId, addressId);

        // then
        InOrder inOrder = inOrder(queryCustomerService, customerRepository, applicationEventPublisher);
        inOrder.verify(queryCustomerService).retrieveCustomerById(customerId);
        inOrder.verify(customerRepository).save(any(Customer.class));
        inOrder.verify(applicationEventPublisher).publishEvent(any(CustomerAddressDeletedEvent.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_throw_exception_deleteCustomerAddress_when_customer_not_exist() {
        // given
        Long customerId = 1l;
        Long addressId = 1l;

        when(queryCustomerService.retrieveCustomerById(customerId)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(BusinessException.class, () -> sut.deleteCustomerAddress(customerId, addressId));

        // then
        assertThat(exception).isInstanceOf(BusinessException.class);
        assertThat(exception).hasMessage("Requested Customer is not exist");

        InOrder inOrder = inOrder(queryCustomerService, customerRepository, applicationEventPublisher);
        inOrder.verify(queryCustomerService).retrieveCustomerById(customerId);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_throw_exception_deleteCustomerAddress_when_customer_address_not_exist() {
        // given
        Long customerId = 1l;
        Long addressId = 1l;

        Address mockAddress = new Address();
        mockAddress.setId(3l);
        Customer mockCustomer = new Customer();
        mockCustomer.getAddressSet().add(mockAddress);
        when(queryCustomerService.retrieveCustomerById(customerId)).thenReturn(Optional.of(mockCustomer));


        // when
        Exception exception = assertThrows(BusinessException.class, () -> sut.deleteCustomerAddress(customerId, addressId));

        // then
        assertThat(exception).isInstanceOf(BusinessException.class);
        assertThat(exception).hasMessage("Requested Customer Address is not exist");

        InOrder inOrder = inOrder(queryCustomerService, customerRepository, applicationEventPublisher);
        inOrder.verify(queryCustomerService).retrieveCustomerById(customerId);
        inOrder.verifyNoMoreInteractions();
    }
}
package com.delivery.customer.service.domain.customer;

import com.delivery.customer.BaseUnitTest;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

class QueryCustomerServiceTest extends BaseUnitTest {

    @InjectMocks
    private QueryCustomerService sut;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    void should_retrieveCustomerById() {
        // given
        Optional<Customer> mockCustomer = Optional.of(new Customer());
        when(customerRepository.findById(1l)).thenReturn(mockCustomer);

        // when
        sut.retrieveCustomerById(1l);

        // then
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findById(1l);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieveCustomerByContacts() {
        // given
        String email = "mock@email.com";
        String phoneNumber = "905554443322";

        Optional<Customer> mockCustomer = Optional.of(new Customer());
        when(customerRepository.findByEmailAndPhoneNumber("mock@email.com", "905554443322")).thenReturn(mockCustomer);

        // when
        sut.retrieveCustomerByContacts(email, phoneNumber);

        // then
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findByEmailAndPhoneNumber("mock@email.com", "905554443322");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_paginationOverCustomer() {
        // given
        when(customerRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        // when
        sut.paginationOverCustomer(1, 50);

        // then
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findAll(any(PageRequest.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_searchCustomersByCity() {
        // given
        String name = "mock-city";

        List<Customer> mockCustomerList = new ArrayList<>();
        when(customerRepository.findAllByAddressSetCityLike("mock-city")).thenReturn(mockCustomerList);

        // when
        sut.searchCustomersByCity(name);

        // then
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findAllByAddressSetCityLike("mock-city");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_searchCustomersByPhonePrefix() {
        // given
        String prefix = "90542";

        List<Customer> mockCustomerList = new ArrayList<>();
        when(customerRepository.findAllByPhoneNumberStartsWith("90542")).thenReturn(mockCustomerList);

        // when
        sut.searchCustomersByPhonePrefix(prefix);

        // then
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findAllByPhoneNumberStartsWith("90542");
        inOrder.verifyNoMoreInteractions();

    }
}
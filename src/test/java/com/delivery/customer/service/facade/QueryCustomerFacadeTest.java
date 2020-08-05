package com.delivery.customer.service.facade;

import com.delivery.customer.BaseUnitTest;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.service.domain.customer.QueryCustomerService;
import com.delivery.customer.service.mapper.MappingCustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

class QueryCustomerFacadeTest extends BaseUnitTest {

    @InjectMocks
    private QueryCustomerFacade sut;

    @Mock
    private QueryCustomerService queryCustomerService;

    @Mock
    private MappingCustomerService mappingCustomerService;


    @Test
    void should_paginationAllCustomers() {
        // given
        Page<Customer> mockCustomer = Page.empty();
        when(queryCustomerService.paginationOverCustomer(1, 50)).thenReturn(mockCustomer);

        // when
        sut.paginationAllCustomers(1, 50);

        // then
        InOrder inOrder = inOrder(queryCustomerService, mappingCustomerService);
        inOrder.verify(queryCustomerService).paginationOverCustomer(1, 50);
        inOrder.verify(mappingCustomerService).mapForPagination(mockCustomer, 1);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieveIndividualCustomer() {
        // given
        long id = 1l;
        Optional<Customer> optionalCustomer = Optional.of(new Customer());
        when(queryCustomerService.retrieveCustomerById(id)).thenReturn(optionalCustomer);

        // when
        sut.retrieveIndividualCustomer(id);

        // then
        InOrder inOrder = inOrder(queryCustomerService, mappingCustomerService);
        inOrder.verify(queryCustomerService).retrieveCustomerById(1l);
        inOrder.verify(mappingCustomerService).mapForRetrieveIndividual(optionalCustomer);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_searchCustomersByCity() {
        // given
        String name = "mock-city";
        List<Customer> mockCustomerList = new ArrayList<>();
        when(queryCustomerService.searchCustomersByCity(name)).thenReturn(mockCustomerList);

        // when
        sut.searchCustomersByCity(name);

        // then
        InOrder inOrder = inOrder(queryCustomerService, mappingCustomerService);
        inOrder.verify(queryCustomerService).searchCustomersByCity("mock-city");
        inOrder.verify(mappingCustomerService).mapForSearch(mockCustomerList);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_searchCustomersByPhonePrefix() {
        // given
        String prefix = "90542";
        List<Customer> mockCustomerList = new ArrayList<>();
        when(queryCustomerService.searchCustomersByPhonePrefix(prefix)).thenReturn(mockCustomerList);

        // when
        sut.searchCustomersByPhonePrefix(prefix);

        // then
        InOrder inOrder = inOrder(queryCustomerService, mappingCustomerService);
        inOrder.verify(queryCustomerService).searchCustomersByPhonePrefix("90542");
        inOrder.verify(mappingCustomerService).mapForSearch(mockCustomerList);
        inOrder.verifyNoMoreInteractions();
    }
}
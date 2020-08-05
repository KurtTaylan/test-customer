package com.delivery.customer.service.validation;

import com.delivery.customer.BaseUnitTest;
import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.enumtype.AddressEnumType;
import com.delivery.customer.model.exception.ValidationException;
import com.delivery.customer.service.domain.customer.QueryCustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ValidationCustomerServiceTest extends BaseUnitTest {


    @InjectMocks
    private ValidationCustomerService sut;

    @Mock
    private QueryCustomerService queryCustomerService;


    @Test
    void should_validateForCreation() {
        // given
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        customerCreateRequest.setEmail("mock@email.com");
        customerCreateRequest.setPhoneNumber("905554443322");

        when(queryCustomerService.retrieveCustomerByContacts(customerCreateRequest.getEmail(), customerCreateRequest.getPhoneNumber()))
                .thenReturn(Optional.empty());

        // when - then
        assertDoesNotThrow(() -> sut.validateForCreation(customerCreateRequest));
    }

    @Test
    void should_throw_exception_validateForCreation_when_customer_exists() {
        // given
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        customerCreateRequest.setEmail("mock@email.com");
        customerCreateRequest.setPhoneNumber("905554443322");

        when(queryCustomerService.retrieveCustomerByContacts(customerCreateRequest.getEmail(), customerCreateRequest.getPhoneNumber()))
                .thenReturn(Optional.of(new Customer()));

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> sut.validateForCreation(customerCreateRequest));

        // then
        assertThat(exception).isInstanceOf(ValidationException.class);
        assertThat(exception).hasMessage("Requested Customer is Already Exist!");
    }

    @Test
    void should_validateForCreationAddress() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();
        customerAddressCreateRequest.setType("business");
        customerAddressCreateRequest.setCountry("turkey");
        customerAddressCreateRequest.setCity("ankara");
        customerAddressCreateRequest.setLine("merkez");

        Address mockAddress = new Address();

        Customer mockCustomer = new Customer();
        mockCustomer.setFirstName("first-name");
        mockCustomer.setLastName("last-name");
        mockCustomer.setEmail("mock@email.com");
        mockCustomer.setPhoneNumber("905554443322");
        mockCustomer.getAddressSet().add(mockAddress);

        when(queryCustomerService.retrieveCustomerById(customerId))
                .thenReturn(Optional.of(mockCustomer));

        // when - then
        assertDoesNotThrow(() -> sut.validateForCreationAddress(customerId, customerAddressCreateRequest));
    }

    @Test
    void should_throw_exception_validateForCreationAddress_when_type_is_invalid() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();
        customerAddressCreateRequest.setType("invalid-type");

        customerAddressCreateRequest.setCountry("turkey");
        customerAddressCreateRequest.setCity("ankara");
        customerAddressCreateRequest.setLine("merkez");

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> sut.validateForCreationAddress(customerId, customerAddressCreateRequest));

        // then
        assertThat(exception).isInstanceOf(ValidationException.class);
        assertThat(exception).hasMessage("Address Type is wrong, Options: 1- business, 2- home");
    }

    @Test
    void should_throw_exception_validateForCreationAddress_when_customer_not_exist() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();
        customerAddressCreateRequest.setType("business");
        customerAddressCreateRequest.setCountry("turkey");
        customerAddressCreateRequest.setCity("ankara");
        customerAddressCreateRequest.setLine("merkez");

        when(queryCustomerService.retrieveCustomerById(customerId)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> sut.validateForCreationAddress(customerId, customerAddressCreateRequest));

        // then
        assertThat(exception).isInstanceOf(ValidationException.class);
        assertThat(exception).hasMessage("Customer is not Exist!");
    }

    @Test
    void should_throw_exception_validateForCreationAddress_when_customer_address_alread__exist() {
        // given
        Long customerId = 1l;
        CustomerAddressCreateRequest customerAddressCreateRequest = new CustomerAddressCreateRequest();
        customerAddressCreateRequest.setType("business");
        customerAddressCreateRequest.setCountry("turkey");
        customerAddressCreateRequest.setCity("ankara");
        customerAddressCreateRequest.setLine("merkez");

        Address mockAddress = new Address();
        mockAddress.setType(AddressEnumType.business);
        mockAddress.setCountry("turkey");
        mockAddress.setCity("ankara");
        mockAddress.setLine("merkez");

        Customer mockCustomer = new Customer();
        mockCustomer.setFirstName("first-name");
        mockCustomer.setLastName("last-name");
        mockCustomer.setEmail("mock@email.com");
        mockCustomer.setPhoneNumber("905554443322");
        mockCustomer.getAddressSet().add(mockAddress);

        when(queryCustomerService.retrieveCustomerById(customerId))
                .thenReturn(Optional.of(mockCustomer));

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> sut.validateForCreationAddress(customerId, customerAddressCreateRequest));

        // then
        assertThat(exception).isInstanceOf(ValidationException.class);
        assertThat(exception).hasMessage("Requested Customer Address is Already Exist!");
    }
}
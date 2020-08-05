package com.delivery.customer.controller;


import com.delivery.customer.BaseRESTIT;
import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.dto.response.CustomerAddressCreateResponse;
import com.delivery.customer.model.dto.response.CustomerAddressDeleteResponse;
import com.delivery.customer.model.dto.response.CustomerCreateResponse;
import com.delivery.customer.model.event.domain.address.CustomerAddressCreatedEvent;
import com.delivery.customer.model.event.domain.address.CustomerAddressDeletedEvent;
import com.delivery.customer.model.event.domain.customer.CustomerCreatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class CommandCustomerControllerIT extends BaseRESTIT {


    @Test
    public void should_createCustomer() {
        // given
        CustomerCreateRequest request = new CustomerCreateRequest();
        request.setFirstName("mock-firstname");
        request.setLastName("mock-lastname");
        request.setEmail("mock@email.com");
        request.setPhoneNumber("905554443322");

        // when
        ResponseEntity<CustomerCreateResponse> responseEntity = testRestTemplate
                .postForEntity(URI.create("/customer"), request, CustomerCreateResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerCreateResponse response = responseEntity.getBody();
        assertThat(response.getCustomer()).isNotNull();
        assertThat(response.getCustomer().getId()).isNotNull();
        assertThat(response.getCustomer().getFirstName()).isEqualTo("mock-firstname");
        assertThat(response.getCustomer().getLastName()).isEqualTo("mock-lastname");
        assertThat(response.getCustomer().getEmail()).isEqualTo("mock@email.com");
        assertThat(response.getCustomer().getPhoneNumber()).isEqualTo("905554443322");

        verifyPublishedEvents(1, CustomerCreatedEvent.class);
    }

    @Test
    public void should_createCustomerAddress() {
        // given
        CustomerAddressCreateRequest request = new CustomerAddressCreateRequest();
        request.setCountry("sweden");
        request.setCity("kristianstad");
        request.setType("business");
        request.setLine("line4");

        // when
        ResponseEntity<CustomerAddressCreateResponse> responseEntity = testRestTemplate
                .postForEntity(URI.create("/customer/1/address"), request, CustomerAddressCreateResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerAddressCreateResponse response = responseEntity.getBody();
        assertThat(response.getAddress()).isNotNull();
        assertThat(response.getAddress().getId()).isNotNull();
        assertThat(response.getAddress().getCountry()).isEqualTo("sweden");
        assertThat(response.getAddress().getCity()).isEqualTo("kristianstad");
        assertThat(response.getAddress().getType()).isEqualTo("business");
        assertThat(response.getAddress().getLine()).isEqualTo("line4");

        verifyPublishedEvents(1, CustomerAddressCreatedEvent.class);
    }

    @Test
    public void should_deleteCustomerAddress() {
        // given
        Long customerId = 1l;
        Long addressId = 1l;

        // when
        ResponseEntity<CustomerAddressDeleteResponse> responseEntity = testRestTemplate
                .exchange(URI.create("/customer/" + customerId + "/address/" + addressId), HttpMethod.DELETE, HttpEntity.EMPTY, CustomerAddressDeleteResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerAddressDeleteResponse response = responseEntity.getBody();
        assertThat(response.getDeletedCustomerId()).isEqualTo(customerId);
        assertThat(response.getDeletedAddressId()).isEqualTo(addressId);

        verifyPublishedEvents(1, CustomerAddressDeletedEvent.class);
    }
}
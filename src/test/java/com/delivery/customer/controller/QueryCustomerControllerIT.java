package com.delivery.customer.controller;

import com.delivery.customer.BaseRESTIT;
import com.delivery.customer.model.dto.response.CustomerIndividualResponse;
import com.delivery.customer.model.dto.response.CustomerPaginationResponse;
import com.delivery.customer.model.dto.response.CustomerSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class QueryCustomerControllerIT extends BaseRESTIT {


    @Test
    void should_paginationAllCustomers() {
        // given
        int page = 1;
        int count = 20;

        // when
        ResponseEntity<CustomerPaginationResponse> responseEntity = testRestTemplate
                .getForEntity(URI.create("/customer?page=" + page + "&count=" + count), CustomerPaginationResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerPaginationResponse response = responseEntity.getBody();
        assertThat(response.getPage()).isEqualTo(1);
        assertThat(response.getCount()).isEqualTo(20);
        assertThat(response.getTotalCount()).isEqualTo(4);
        assertThat(response.getList()).hasSize(4);
    }

    @Test
    void should_retrieveIndividualCustomer() {
        // given
        int id = 2;

        // when
        ResponseEntity<CustomerIndividualResponse> responseEntity = testRestTemplate
                .getForEntity(URI.create("/customer/" + id), CustomerIndividualResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerIndividualResponse response = responseEntity.getBody();
        assertThat(response.getCustomer()).isNotNull();
        assertThat(response.getCustomer().getFirstName()).isEqualTo("firstname-2");
        assertThat(response.getCustomer().getLastName()).isEqualTo("lastname-2");
        assertThat(response.getCustomer().getEmail()).isEqualTo("2@email.com");
        assertThat(response.getCustomer().getPhoneNumber()).isEqualTo("905554443322");
    }

    @Test
    void should_searchCustomersByCity() {
        // given
        String city = "stockholm";

        // when
        ResponseEntity<CustomerSearchResponse> responseEntity = testRestTemplate
                .getForEntity(URI.create("/city/" + city ), CustomerSearchResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerSearchResponse response = responseEntity.getBody();
        assertThat(response.getTotalCount()).isEqualTo(2);
        assertThat(response.getList()).hasSize(2);
    }

    @Test
    void should_searchCustomersByPhonePrefix() {
        // given
        String prefix = "90555444332";

        // when
        ResponseEntity<CustomerSearchResponse> responseEntity = testRestTemplate
                .getForEntity(URI.create("/phone/" + prefix ), CustomerSearchResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        CustomerSearchResponse response = responseEntity.getBody();
        assertThat(response.getTotalCount()).isEqualTo(4);
        assertThat(response.getList()).hasSize(4);
    }
}
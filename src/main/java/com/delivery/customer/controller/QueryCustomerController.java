package com.delivery.customer.controller;

import com.delivery.customer.model.dto.request.CustomerPaginationRequest;
import com.delivery.customer.model.dto.response.CustomerIndividualResponse;
import com.delivery.customer.model.dto.response.CustomerPaginationResponse;
import com.delivery.customer.model.dto.response.CustomerSearchResponse;
import com.delivery.customer.service.facade.QueryCustomerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer Queries")
@RequiredArgsConstructor
@RestController
public class QueryCustomerController {

    private final QueryCustomerFacade queryCustomerFacade;


    @Operation(summary = "All Customer")
    @GetMapping("/customer")
    @ResponseBody
    public CustomerPaginationResponse paginationAllCustomers(@Validated CustomerPaginationRequest customerPaginationRequest) {
        return queryCustomerFacade.paginationAllCustomers(customerPaginationRequest.getPage(), customerPaginationRequest.getCount());
    }

    @Operation(summary = "Individual Customer")
    @GetMapping("/customer/{id}")
    @ResponseBody
    public CustomerIndividualResponse retrieveIndividualCustomer(@PathVariable Long id) {
        return queryCustomerFacade.retrieveIndividualCustomer(id);
    }

    @Operation(summary = "Search Customer by City")
    @GetMapping("/city/{name}")
    @ResponseBody
    public CustomerSearchResponse searchCustomersByCity(@PathVariable String name) {
        return queryCustomerFacade.searchCustomersByCity(name);
    }

    @Operation(summary = "Search Customer by Phone Prefix")
    @GetMapping("/phone/{prefix}")
    @ResponseBody
    public CustomerSearchResponse searchCustomersByPhonePrefix(@PathVariable String prefix) {
        return queryCustomerFacade.searchCustomersByPhonePrefix(prefix);
    }
}

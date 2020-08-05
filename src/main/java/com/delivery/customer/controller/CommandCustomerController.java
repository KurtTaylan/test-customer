package com.delivery.customer.controller;

import com.delivery.customer.model.dto.request.CustomerAddressCreateRequest;
import com.delivery.customer.model.dto.request.CustomerCreateRequest;
import com.delivery.customer.model.dto.response.CustomerAddressCreateResponse;
import com.delivery.customer.model.dto.response.CustomerAddressDeleteResponse;
import com.delivery.customer.model.dto.response.CustomerCreateResponse;
import com.delivery.customer.service.facade.CommandCustomerFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Customer", description = "All Customer Related")
@RequiredArgsConstructor
@RequestMapping("/customer")
@RestController
public class CommandCustomerController {

    private final CommandCustomerFacade commandCustomerFacade;


    @Operation(summary = "Create Customer")
    @PostMapping
    @ResponseBody
    public CustomerCreateResponse createCustomer(@RequestBody @Validated CustomerCreateRequest customerCreateRequest) {
        return commandCustomerFacade.createCustomer(customerCreateRequest);
    }

    @Operation(summary = "Create Customer Address")
    @PostMapping(value = "/{customerId}/address")
    @ResponseBody
    public CustomerAddressCreateResponse createCustomerAddress(@PathVariable Long customerId, @RequestBody CustomerAddressCreateRequest customerAddressCreateRequest) {
        return commandCustomerFacade.createCustomerAddress(customerId, customerAddressCreateRequest);
    }

    @Operation(summary = "Delete Customer Address")
    @DeleteMapping(value = "/{customerId}/address/{addressId}")
    @ResponseBody
    public CustomerAddressDeleteResponse deleteCustomerAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
        return commandCustomerFacade.deleteCustomerAddress(customerId, addressId);
    }
}

package com.delivery.customer.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerAddressCreateRequest {

    @NotNull
    private String type;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String line;
}

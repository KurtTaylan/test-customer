package com.delivery.customer.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CustomerCreateRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Pattern(regexp="(^$|[0-9]{8,14})")
    @NotNull
    private String phoneNumber;

    @Email
    @NotNull
    private String email;
}

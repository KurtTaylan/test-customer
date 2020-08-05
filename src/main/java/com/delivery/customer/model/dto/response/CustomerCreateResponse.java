package com.delivery.customer.model.dto.response;

import com.delivery.customer.model.dto.domain.CustomerDto;
import lombok.Data;

@Data
public class CustomerCreateResponse {

    private CustomerDto customer;
}

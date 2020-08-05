package com.delivery.customer.model.dto.response;

import lombok.Data;

@Data
public class CustomerAddressDeleteResponse {

    private Long deletedCustomerId;
    private Long deletedAddressId;
}

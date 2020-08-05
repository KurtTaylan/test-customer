package com.delivery.customer.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CustomerPaginationRequest {

    @Min(1)
    private int page = 1;

    @Min(5)
    @Max(50)
    private int count = 20;
}

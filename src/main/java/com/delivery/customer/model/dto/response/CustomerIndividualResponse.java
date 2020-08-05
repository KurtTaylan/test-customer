package com.delivery.customer.model.dto.response;

import com.delivery.customer.model.dto.BaseDto;
import com.delivery.customer.model.dto.domain.CustomerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerIndividualResponse extends BaseDto {

    private CustomerDto customer;

}

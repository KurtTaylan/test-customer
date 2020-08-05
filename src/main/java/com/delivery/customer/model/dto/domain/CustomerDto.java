package com.delivery.customer.model.dto.domain;

import com.delivery.customer.model.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomerDto extends BaseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}

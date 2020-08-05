package com.delivery.customer.model.dto.domain;

import com.delivery.customer.model.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AddressDto extends BaseDto {

    private Long id;
    private String type;
    private String city;
    private String country;
    private String line;
}

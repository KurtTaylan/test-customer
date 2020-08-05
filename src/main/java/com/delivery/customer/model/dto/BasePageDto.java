package com.delivery.customer.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Data
public abstract class BasePageDto extends BaseDto {

    private int page;
    private int count;
    private long totalCount;
}

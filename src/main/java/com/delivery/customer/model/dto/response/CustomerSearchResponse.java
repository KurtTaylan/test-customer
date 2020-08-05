package com.delivery.customer.model.dto.response;

import com.delivery.customer.model.dto.BaseDto;
import com.delivery.customer.model.dto.domain.CustomerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerSearchResponse extends BaseDto {

    private int totalCount;
    private List<CustomerDto> list;

    public int getTotalCount() {
        return totalCount;
    }
}

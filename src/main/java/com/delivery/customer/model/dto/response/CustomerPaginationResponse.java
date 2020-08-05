package com.delivery.customer.model.dto.response;

import com.delivery.customer.model.dto.BasePageDto;
import com.delivery.customer.model.dto.domain.CustomerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerPaginationResponse extends BasePageDto {

    private List<CustomerDto> list;

}

package com.delivery.customer.model.mapper;

import com.delivery.customer.model.dto.domain.CustomerDto;
import com.delivery.customer.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    List<CustomerDto> toDtoList(List<Customer> customerSet);

    CustomerDto toDto(Customer customer);

}

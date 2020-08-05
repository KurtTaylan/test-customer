package com.delivery.customer.model.mapper;

import com.delivery.customer.model.dto.domain.CustomerDto;
import com.delivery.customer.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    Set<CustomerDto> toDtoSet(Set<Customer> customerSet);

    CustomerDto toDto(Customer customer);

}

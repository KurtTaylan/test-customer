package com.delivery.customer.model.mapper;

import com.delivery.customer.model.dto.domain.AddressDto;
import com.delivery.customer.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Set<AddressDto> toDtoSet(Set<Address> addressSet);

    @Mapping(target = "type", expression = "java(address.getType().toString())")
    AddressDto toDto(Address address);
}

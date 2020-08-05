package com.delivery.customer.model.event.payload.address;

import com.delivery.customer.model.entity.Address;
import com.delivery.customer.model.event.payload.EventPayload;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class AddressPayload extends EventPayload {

    Address address;
}

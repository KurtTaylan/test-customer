package com.delivery.customer.model.event.domain.address;

import com.delivery.customer.model.event.BaseEvent;
import com.delivery.customer.model.event.payload.address.AddressPayload;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString
@EqualsAndHashCode(callSuper = true)
@Value
public class CustomerAddressDeletedEvent extends BaseEvent<AddressPayload> {

    public CustomerAddressDeletedEvent(Object source, AddressPayload payload) {
        super(source, payload);
    }
}

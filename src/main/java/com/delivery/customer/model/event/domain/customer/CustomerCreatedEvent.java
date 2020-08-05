package com.delivery.customer.model.event.domain.customer;

import com.delivery.customer.model.event.BaseEvent;
import com.delivery.customer.model.event.payload.customer.CustomerPayload;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString
@EqualsAndHashCode(callSuper = true)
@Value
public class CustomerCreatedEvent extends BaseEvent<CustomerPayload> {

    public CustomerCreatedEvent(Object source, CustomerPayload payload) {
        super(source, payload);
    }
}

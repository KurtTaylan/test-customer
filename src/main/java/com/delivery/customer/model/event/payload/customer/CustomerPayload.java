package com.delivery.customer.model.event.payload.customer;

import com.delivery.customer.model.entity.Customer;
import com.delivery.customer.model.event.payload.EventPayload;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CustomerPayload extends EventPayload {

    Customer customer;
}

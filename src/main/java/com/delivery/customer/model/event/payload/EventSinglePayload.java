package com.delivery.customer.model.event.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class EventSinglePayload extends EventPayload {

}

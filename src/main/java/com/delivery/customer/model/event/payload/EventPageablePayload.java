package com.delivery.customer.model.event.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class EventPageablePayload<L> extends EventPayload {

    protected int page;
    protected int count;
    protected List<L> list;
}

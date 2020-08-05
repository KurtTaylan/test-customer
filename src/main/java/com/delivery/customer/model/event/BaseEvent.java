package com.delivery.customer.model.event;

import com.delivery.customer.model.event.payload.EventPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@ToString
@Getter
@Setter
public abstract class BaseEvent<P extends EventPayload> extends ApplicationEvent {

    protected UUID id = UUID.randomUUID();
    protected int version = 1;
    protected P payload;

    public BaseEvent(Object source) {
        super(source);
    }

    public BaseEvent(Object source, P payload) {
        this(source);
        this.payload = payload;
    }
}

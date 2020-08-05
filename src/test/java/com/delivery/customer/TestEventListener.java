package com.delivery.customer;

import com.delivery.customer.model.event.BaseEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestEventListener implements ApplicationListener<BaseEvent> {

    @Override
    public void onApplicationEvent(BaseEvent event) {

    }
}

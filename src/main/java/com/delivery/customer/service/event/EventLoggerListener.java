package com.delivery.customer.service.event;

import com.delivery.customer.model.event.BaseEvent;
import com.delivery.customer.model.event.payload.EventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventLoggerListener implements ApplicationListener<BaseEvent<? extends EventPayload>> {


    @Override
    public void onApplicationEvent(BaseEvent<? extends EventPayload> event) {
        log.info("EVENT-LISTEN: {}, with ID: {}, version: {}, timestamp: {}, payload: {}",
                event.getClass().getSimpleName(),
                event.getId(),
                event.getVersion(),
                event.getTimestamp(),
                event.getPayload().toString());
    }
}

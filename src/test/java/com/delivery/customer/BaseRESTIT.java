package com.delivery.customer;

import com.delivery.customer.TestEventListener;
import com.delivery.customer.model.event.BaseEvent;
import com.delivery.customer.model.event.payload.EventPayload;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseRESTIT {

    @LocalServerPort
    protected int randomServerPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @SpyBean
    private TestEventListener testEventListener;

    @Captor
    protected ArgumentCaptor<BaseEvent<? extends EventPayload>> publishEventCaptor;



    protected void verifyPublishedEvents(int times, Class<? extends BaseEvent<? extends EventPayload>>... events) {
        Mockito.verify(testEventListener, Mockito.times(times)).onApplicationEvent(publishEventCaptor.capture());
        List<BaseEvent<? extends EventPayload>> capturedEvents = publishEventCaptor.getAllValues();

        assertThat(capturedEvents.size()).isEqualTo(events.length);
        for (int i = 0; i < capturedEvents.size(); i++) {
            assertThat(capturedEvents.get(i)).isInstanceOf(events[i]);
        }
    }
}

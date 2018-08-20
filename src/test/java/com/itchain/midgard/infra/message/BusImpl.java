package com.itchain.midgard.infra.message;

import com.itchain.midgard.common.Event;
import com.itchain.midgard.domain.Bus;
import org.springframework.stereotype.Component;

@Component
public class BusImpl implements Bus {
    @Override
    public void Publish(Event event) {

    }

    @Override
    public void Subscribe(String topic, Event event) {

    }
}

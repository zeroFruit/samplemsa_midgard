package com.itchain.midgard.domain;

import com.itchain.midgard.common.Event;

public interface Bus {
    void Publish(Event event);
    void Subscribe(String topic, Event event);
}

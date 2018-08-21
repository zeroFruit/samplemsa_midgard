package com.itchain.midgard.domain;

import com.itchain.midgard.common.Event;

import java.util.List;

public interface EventStore{
    EntityWithIdAndEventList save(String aggregateID, List<Event> event);
    EntityWithIdAndEventList load(String aggregateID);
}

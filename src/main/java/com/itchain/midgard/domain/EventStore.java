package com.itchain.midgard.domain;

import com.itchain.midgard.common.Event;

import java.util.List;

public interface EventStore{
    ReadWriteSet save(String aggregateID, Event event);
    ReadWriteSet load(String aggregateID);
}

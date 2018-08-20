package com.itchain.midgard.common;


public interface EventRepository {
    void Save(Event event);
    Aggregate Load(String id);
}

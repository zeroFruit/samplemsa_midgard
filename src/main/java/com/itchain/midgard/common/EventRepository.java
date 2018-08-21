package com.itchain.midgard.common;


import java.util.List;

public interface EventRepository {
    void Save(Aggregate aggregate);
    List<Event> Load(String id);
}

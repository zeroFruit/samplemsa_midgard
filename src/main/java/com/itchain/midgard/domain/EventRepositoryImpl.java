package com.itchain.midgard.domain;

import com.itchain.midgard.common.Aggregate;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.common.EventRepository;
import com.itchain.midgard.domain.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryImpl implements EventRepository {
    @Autowired
    EventStore store;
    @Autowired
    Bus bus;

    @Override
    public void Save(Event event) {
        String aggregateID = event.GetID();

        if (aggregateID.isEmpty()) {
            return;
        }
        store.save(aggregateID, event);

        bus.Publish(event);
    }

    @Override
    public Aggregate Load(String id) {
        return null;
    }
}

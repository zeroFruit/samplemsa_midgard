package com.itchain.midgard.infra.store;

import com.itchain.midgard.common.Aggregate;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.common.EventRepository;
import com.itchain.midgard.domain.EntityWithIdAndEventList;
import com.itchain.midgard.domain.EventStore;
import com.itchain.midgard.exception.AggregateIDIsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRepositoryImpl implements EventRepository {
    @Autowired
    EventStore store;

    @Override
    public void Save(Aggregate aggregate) {
        if (isAggregateIdEmpty(aggregate)) {
            throw new AggregateIDIsEmptyException();
        }

        store.save(aggregate.getID(), aggregate.getEventList());
    }

    private boolean isAggregateIdEmpty(Aggregate aggregate) {
        String aggregateID = aggregate.getID();
        return aggregateID == null || aggregateID.isEmpty();
    }

    @Override
    public List<Event> Load(String id) {
        EntityWithIdAndEventList entity = store.load(id);
        return entity.getEventList();
    }


}

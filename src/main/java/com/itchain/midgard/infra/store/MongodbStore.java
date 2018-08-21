package com.itchain.midgard.infra.store;

import com.itchain.midgard.common.Event;
import com.itchain.midgard.domain.EventStore;
import com.itchain.midgard.domain.MongoClient;
import com.itchain.midgard.domain.EntityWithIdAndEventList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MongodbStore implements EventStore {
    @Autowired
    MongoClient client;

    @Override
    public EntityWithIdAndEventList save(String aggregateID, List<Event> eventList) {
        EntityWithIdAndEventList entity = load(aggregateID);
        if (entity == null) {
            entity = new EntityWithIdAndEventList();
        }

        entity.appendEventList(eventList);

        return client.save(entity);
    }

    @Override
    public EntityWithIdAndEventList load(String aggregateID) {
        Optional<EntityWithIdAndEventList> option = client.findById(aggregateID);
        if (!option.isPresent()) {
            return null;
        }
        return option.get();
    }
}

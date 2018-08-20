package com.itchain.midgard.infra.store;

import com.itchain.midgard.common.Aggregate;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.domain.EventStore;
import com.itchain.midgard.domain.MongoClient;
import com.itchain.midgard.domain.ReadWriteSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
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
    public ReadWriteSet save(String aggregateID, Event event) {
        ReadWriteSet set = load(aggregateID);
        if (set == null) {
            set = new ReadWriteSet();
        }

        set.addEvent(event);

        return client.save(set);
    }

    @Override
    public ReadWriteSet load(String aggregateID) {
        Optional<ReadWriteSet> option = client.findById(aggregateID);
        if (!option.isPresent()) {
            return null;
        }
        return option.get();
    }
}

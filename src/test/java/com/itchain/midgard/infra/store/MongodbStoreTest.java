package com.itchain.midgard.infra.store;

import com.itchain.midgard.MidgardApplication;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.domain.MongoClient;
import com.itchain.midgard.domain.EntityWithIdAndEventList;
import com.itchain.midgard.exception.AggregateIDIsEmptyException;
import com.itchain.midgard.mock.MockEvent;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MidgardApplication.class)
public class MongodbStoreTest {
    @Autowired
    MongodbStore store;
    @Autowired
    MongoClient client;

    @After
    public void cleanUp() {
        client.deleteAll();
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        List<Event> eventList = new ArrayList<>();
        MockEvent event01 = new MockEvent("event01");
        MockEvent event02 = new MockEvent("event02");

        eventList.add(event01);
        eventList.add(event02);

        EntityWithIdAndEventList set = store.save("1", eventList);

        assertEquals(2, set.getEventList().size());
    }

    @Test
    public void load_basic() {
        List<Event> eventList = new ArrayList<>();
        MockEvent event01 = new MockEvent("event01");
        MockEvent event02 = new MockEvent("event02");

        eventList.add(event01);
        eventList.add(event02);

        EntityWithIdAndEventList set = store.save("1", eventList);

        EntityWithIdAndEventList result = store.load(set.getId());

        assertEquals(set.getId(), result.getId());
        assertEquals(2, result.getEventList().size());
    }
}

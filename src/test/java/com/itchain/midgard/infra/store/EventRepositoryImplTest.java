package com.itchain.midgard.infra.store;

import com.itchain.midgard.MidgardApplication;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.common.EventRepository;
import com.itchain.midgard.domain.EntityWithIdAndEventList;
import com.itchain.midgard.domain.MongoClient;
import com.itchain.midgard.exception.AggregateIDIsEmptyException;
import com.itchain.midgard.mock.AddEvent;
import com.itchain.midgard.mock.MockAggregate;
import com.itchain.midgard.mock.MockEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MidgardApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class EventRepositoryImplTest {
    @MockBean
    MongodbStore store;
    @Autowired
    MongoClient client;
    @Autowired
    EventRepository repo;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

        MockEvent event1 = new MockEvent("1");
        MockAggregate aggregate = new MockAggregate(eventList);
        aggregate.setId("1");

        repo.Save(aggregate);
    }

    @Test
    public void save_when_aggregate_id_is_not_provided() {
        expectedException.expect(AggregateIDIsEmptyException.class);
        expectedException.expectMessage("Aggregate ID is empty");
        MockAggregate aggregate = new MockAggregate();
        repo.Save(aggregate);
    }

    @Test
    public void load_basic() {
        EntityWithIdAndEventList entity = new EntityWithIdAndEventList();
        entity.setId("1");

        AddEvent add1 = new AddEvent("1");

        entity.addEvent(add1);

        when(store.load("1")).thenReturn(entity);

        List<Event> eventList =  repo.Load("1");

        assertEquals(1, eventList.size());
        assertEquals("AddEvent", eventList.get(0).getClass().getSimpleName());
    }
}

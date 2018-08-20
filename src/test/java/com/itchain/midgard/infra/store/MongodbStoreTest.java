package com.itchain.midgard.infra.store;

import com.itchain.midgard.MidgardApplication;
import com.itchain.midgard.common.Event;
import com.itchain.midgard.domain.MongoClient;
import com.itchain.midgard.domain.ReadWriteSet;
import com.itchain.midgard.mock.MockEvent;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

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
        ReadWriteSet set = store.save("1", new MockEvent("event01"));

        assertEquals(1, set.getEventList().size());
    }

    @Test
    public void load_basic() {
        ReadWriteSet set = store.save("1", new MockEvent("event01"));

        ReadWriteSet result = store.load(set.getId());

        assertEquals(set.getId(), result.getId());
    }
}

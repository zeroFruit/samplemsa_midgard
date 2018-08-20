package com.itchain.midgard.mock;

import com.itchain.midgard.common.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
public class MockEvent implements Event {
    @Id
    private String id;
    public MockEvent(String id) {
        this.id = id;
    }
    @Override
    public String GetID() {
        return id;
    }

    @Override
    public String GetBodyJson() {
        return null;
    }
}

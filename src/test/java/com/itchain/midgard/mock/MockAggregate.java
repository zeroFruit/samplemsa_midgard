package com.itchain.midgard.mock;

import com.itchain.midgard.common.Aggregate;
import com.itchain.midgard.common.Event;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class MockAggregate extends Aggregate {
    @Id
    @NonNull
    private String id;
    private int value = 0;

    public MockAggregate(List<Event> eventList) {
        super(eventList);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void apply(Event event) {
        String className = event.getClass().getSimpleName();
        System.out.println("class name >> " + className);

        if (className == "AddEvent") {

        }
    }

}

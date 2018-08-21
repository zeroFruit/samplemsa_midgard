package com.itchain.midgard.mock;

import com.itchain.midgard.common.Event;
import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SubtractEvent implements Event {
    @Id
    @NonNull
    private String id;
    @Override
    public String GetID() {
        return null;
    }

    @Override
    public String GetBodyJson() {
        return null;
    }
}

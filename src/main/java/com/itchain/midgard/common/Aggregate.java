package com.itchain.midgard.common;

public interface Aggregate {
    String GetID();
    void On(Event event);
}

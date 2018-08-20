package com.itchain.midgard.common;

import com.google.gson.Gson;

public interface Event {
    String GetID();
    default String GetBodyJson(){
        Gson gSon = new Gson();
        return gSon.toJson(this);
    }
}

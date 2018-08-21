package com.itchain.midgard.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Aggregate {
    private List<Event> eventList;
    private Map<String, Method> methodMap = new HashMap<>();
    public static final String ROUTE_METHOD_NAME = "on";

    protected Aggregate(){
        eventList = new ArrayList<>();
    }

    protected Aggregate(List<Event> anEventStream){
        this();

        for (Event event : anEventStream) {
            this.routeOn(event);
        }
    }

    public abstract String getID();
    public void clearEvents(){
        this.eventList.clear();
    }

    public void apply(Event event) {

    }

    protected void routeOn(Event event) {

    }

    private Method addMethod(
            String aKey,
            Class<? extends Aggregate> aggregateType,
            Class<? extends Event> eventType) {
        try {
            Method method = this.getMethod(aggregateType, eventType);

            method.setAccessible(true);

            methodMap.put(aKey, method);

            return method;

        } catch (Exception e) {
            throw new IllegalArgumentException("method or aggregate type err : "+e.getMessage());
        }
    }

    private Method getMethod(
            Class<? extends Aggregate> aggregateType,
            Class<? extends Event> eventType){

        Method method = null;

        try {
            //protected,private
            method = aggregateType.getDeclaredMethod(
                    ROUTE_METHOD_NAME,
                    eventType);

        } catch (Exception e) {
            //public
            try {
                method = aggregateType.getMethod(
                        ROUTE_METHOD_NAME,
                        eventType);

            }catch (NoSuchMethodException e2){
                System.out.println("no such method exception : "+e.getMessage());
            }
        }

        return method;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
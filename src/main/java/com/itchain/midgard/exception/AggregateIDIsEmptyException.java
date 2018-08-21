package com.itchain.midgard.exception;

public class AggregateIDIsEmptyException extends EventRepositoryException {
    public AggregateIDIsEmptyException() {
        super("Aggregate ID is empty");
    }
}

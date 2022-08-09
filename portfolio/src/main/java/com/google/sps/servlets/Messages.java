package com.google.sps.servlets;

public final class Messages {

    private final long id;
    private final String textValue;
    private final long timestamp;

    public Messages(long id, String textValue, long timestamp) {
        this.id = id;
        this.textValue = textValue;
        this.timestamp = timestamp;
    }
}
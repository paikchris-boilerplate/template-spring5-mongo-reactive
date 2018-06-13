package com.springreactive.demo.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Event {
    @Id
    public String id;

    public String name;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Event[id=%s, name='%s']",
                id, name);
    }
}

package org.teamaker.technology.domain;

public class Technology {
    private final String id;
    private final String name;

    public Technology(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return id;
    }
}

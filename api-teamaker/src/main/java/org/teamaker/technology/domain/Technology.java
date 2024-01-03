package org.teamaker.technology.domain;

public class Technology {
    private final String guid;
    private final String name;

    public Technology(String guid, String name) {
        this.guid = guid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }
}

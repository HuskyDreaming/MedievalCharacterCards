package com.huskydreaming.medievalcharactercards.enumerations;

public enum CharacterType {
    AGE("age"),
    FIRST("first name"),
    HEIGHT("height"),
    LAST("last name"),
    MIDDLE("middle name"),
    DESC("description");

    private final String name;

    CharacterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

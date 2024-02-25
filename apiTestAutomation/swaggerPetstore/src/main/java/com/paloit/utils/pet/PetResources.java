package com.paloit.utils.pet;

public enum PetResources {
    ADD_NEW_PET_TO_THE_STORE("/api/v3/pet"),
    DELETE_RESOURCE("/api/v3/pet/"),
    FIND_BY_STATUS("/api/v3/pet/findByStatus"),
    UPDATE_RESOURCE("/api/v3/pet/");
    private final String value;

    PetResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

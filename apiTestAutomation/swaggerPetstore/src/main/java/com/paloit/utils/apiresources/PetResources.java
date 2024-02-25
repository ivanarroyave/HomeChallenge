package com.paloit.utils.apiresources;

import lombok.Getter;

@Getter
public enum PetResources {
    ADD_NEW_PET_TO_THE_STORE("/api/v3/pet"),
    DELETE_RESOURCE("/api/v3/pet/"),
    FIND_BY_STATUS("/api/v3/pet/findByStatus"),
    FIND_PETS_BY_TAGS("/api/v3/pet/findByTags"),
    UPDATE_RESOURCE("/api/v3/pet/");
    private final String value;

    PetResources(String value) {
        this.value = value;
    }
}

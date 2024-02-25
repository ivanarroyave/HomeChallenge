package com.paloit.utils;

import lombok.Getter;

@Getter
public enum ContentType {
    APPLICATION_JSON("Content-Type", "application/json","JSON");

    private final String key;
    private final String mimeType;
    private final String description;

    ContentType(String key, String mimeType, String description) {
        this.key = key;
        this.mimeType = mimeType;
        this.description = description;
    }

}

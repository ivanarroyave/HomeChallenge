package com.paloit.utils.apiresources;

import lombok.Getter;

@Getter
public enum StoreResources {
    STORE_INVENTORY("/api/v3/store/inventory");
    private final String value;

    StoreResources(String value) {
        this.value = value;
    }
}

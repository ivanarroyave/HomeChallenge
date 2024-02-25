package com.paloit.models.store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreModelResponse {
    private int approved;
    private int placed;
    private int delivered;
}

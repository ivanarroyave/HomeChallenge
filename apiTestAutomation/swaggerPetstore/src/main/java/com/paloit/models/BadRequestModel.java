package com.paloit.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestModel {
    private int code;
    private String message;
}

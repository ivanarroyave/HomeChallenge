package com.paloit.models.pet.request;

import com.paloit.models.pet.Category;
import com.paloit.models.pet.Tag;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetModelRequest {
    private int id;
    private String name;
    private Category category;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}

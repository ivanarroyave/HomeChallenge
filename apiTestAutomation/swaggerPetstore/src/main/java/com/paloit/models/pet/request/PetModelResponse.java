package com.paloit.models.pet.request;

import com.paloit.models.pet.Category;
import com.paloit.models.pet.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetModelResponse {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}

package com.paloit.utils;

import com.github.javafaker.Faker;
import com.paloit.models.pet.Category;
import com.paloit.models.pet.Tag;
import com.paloit.models.pet.request.PetModelRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PetRequest {
    public static PetModelRequest petModelRequestObject() {
        Faker faker = new Faker();
        PetModelRequest petModelRequest = new PetModelRequest();
        petModelRequest.setId(new Random().nextInt(10000000));
        petModelRequest.setName(faker.dog().name());

        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");

        petModelRequest.setCategory(category);

        petModelRequest.setPhotoUrls(List.of("/pet/photo/profile.jpg"));

        Tag tagOne = new Tag();
        tagOne.setId(0);
        tagOne.setName("Dog");

        Tag tagTwo = new Tag();
        tagTwo.setId(1);
        tagTwo.setName("Friendly");

        petModelRequest.setTags(Arrays.asList(tagOne, tagTwo));
        petModelRequest.setStatus("available");

        return petModelRequest;
    }
}
